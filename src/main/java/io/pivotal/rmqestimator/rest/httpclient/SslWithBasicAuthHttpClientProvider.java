package io.pivotal.rmqestimator.rest.httpclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.pivotal.rmqestimator.rest.HttpClientProvider;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * SSL Authenticated HTTP Client Provider that not only configures SSL
 * to work against the endpoint, but also performs Basic Auth.
 * 
 * Why such a weird combo?  Well, because you have to with the RabbitMQ
 * Management console since it does not rely on the SSL context to grab
 * the principal.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class SslWithBasicAuthHttpClientProvider implements HttpClientProvider {

	private static final Logger logger = LoggerFactory.getLogger(SslWithBasicAuthHttpClientProvider.class);
	
	protected String keystore;
	
	protected String keystorePassword;

	protected String truststore;
	
	protected String truststorePassword = null;
	
	protected String username;
	
	protected String password;

	/**
	 * Instantiate a SSL+BasicAuth HTTP Client Provider
	 * @param keystore Keystore to use.
	 * @param keystorePassword Keystore password.
	 * @param truststore Truststore to use.
	 * @param truststorePassword Truststore Password.
	 * @param username Username.
	 * @param password Password.
	 */
	public SslWithBasicAuthHttpClientProvider(
			String keystore, 
			String keystorePassword, 
			String truststore,
			String truststorePassword, 
			String username, 
			String password) {
		
		this.keystore = keystore;
		this.keystorePassword = keystorePassword;
		this.truststore = truststore;
		this.truststorePassword = truststorePassword;
		this.username = username;
		this.password = password;
	}

    @Override
    public boolean useSsl() {

        return true;
    }

    /**
	 * Get a fully configured, contextually-enabled, super-heroic, HTTP Client.
	 */
	@Override
	public Client getClient() {
		
		Client client = null;
		
		// Create client configuration object.
		ClientConfig clientConf = new DefaultClientConfig();
		
		// Add the ReceivedMessage Body Handler for deserializing JSON.
		clientConf.getClasses().add(GsonMessageBodyHandler.class);
		
		try {

            String keystoreType = getKeyFormatFromExtension(this.keystore);

			// Keystore
			KeyStore clientCertStore = KeyStore.getInstance(keystoreType);
	        clientCertStore.load(new FileInputStream(this.keystore), this.keystorePassword.toCharArray());
	        
	        // KeyManagerFactory
	        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	        kmf.init(clientCertStore, this.keystorePassword.toCharArray());

            String truststoreType = getKeyFormatFromExtension(this.truststore);

	        // Truststore
	        KeyStore remoteCertStore = KeyStore.getInstance(truststoreType);
	        remoteCertStore.load(
	        		new FileInputStream(this.truststore), 
	        		// It's possible that the truststore password is null.
	        		(this.truststorePassword != null)? this.truststorePassword.toCharArray() : null);
	
	        // TrustManagerFactory
	        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	        tmf.init(remoteCertStore);
	
	        // SSL Context
	        SSLContext context = SSLContext.getInstance("SSLv3");
	        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

	        // Add the Context without a verifier (null).
	        // If you need to do something fancy like, NOT HOST VERIFY (dangerous!),
	        // then implement a verifier.
	        HTTPSProperties prop = new HTTPSProperties(null, context);
	        
	        // Add the properties to the client configuration.
	        clientConf.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, prop);
	        
	        // Create the client.
	        client = Client.create(clientConf);
	        
	        // Add the basic auth filter.
	        if (this.username != null && this.password != null){
				
				client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
			}
	        
		} catch (Exception e){  

			logger.error("Something failed!: {}", e);
		}
		
		// Return the client
		return client;
	}
	
	/**
	 * Using file extensions as the  for Keystore type. 
	 * @param filename Keystore file.
	 * @return Key Format to be used by Keystores.
	 */
	public static String getKeyFormatFromExtension(String filename){
		
		if (filename.toLowerCase().endsWith("jks"))
			return "JKS";
		else if (filename.toLowerCase().endsWith("p12"))
			return "PKCS12";
		else
			return KeyStore.getDefaultType();
	}
}

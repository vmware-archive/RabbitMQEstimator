package io.pivotal.rmqestimator.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.pivotal.rmqestimator.rest.httpclient.BasicAuthHttpClientProvider;
import io.pivotal.rmqestimator.rest.httpclient.SslWithBasicAuthHttpClientProvider;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

@Component
@Scope("Prototype")
public class ConnectionInfo implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7135602562686962753L;

	private String hostName;

    private int port;

    private String userName;

    private String password;

    private boolean useSsl = false;

    private String keystore;

    private String keystorePassword;

    private String truststore;

    private String truststorePassword;

    public ConnectionInfo(){}

    public ConnectionInfo(String hostname, int port, String username, String password){

        this.hostName = hostname;
        this.port = port;
        this.userName = username;
        this.password = password;
        this.useSsl = false;
    }

    public ConnectionInfo(String hostname, int port, String username, String password,
                          String keystore, String keystorePassword, String truststore, String truststorePassword) {

        this.hostName = hostname;
        this.port = port;
        this.userName = username;
        this.password = password;
        this.useSsl = true;
        this.keystore = keystore;
        this.keystorePassword = keystorePassword;
        this.truststore = truststore;
        this.truststorePassword = truststorePassword;
    }

    public String getHostname() {
        return hostName;
    }

    public void setHostname(String hostname) {
        this.hostName = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getTruststore() {
        return truststore;
    }

    public void setTruststore(String truststore) {
        this.truststore = truststore;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public void setTruststorePassword(String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }

    /**
     * Build a connection using the internal connection information.
     * @return
     */
    public RabbitNodeMgmtService buildConnection(){

        HttpClientProvider provider = null;

        if (useSsl){

            provider = new SslWithBasicAuthHttpClientProvider(
                    keystore, keystorePassword, truststore, truststorePassword, userName, password);
        }
        else {

            provider = new BasicAuthHttpClientProvider(userName, password);
        }

        return new RabbitNodeMgmtService(hostName, port, provider);
    }
}

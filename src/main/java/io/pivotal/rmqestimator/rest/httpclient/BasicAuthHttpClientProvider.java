package io.pivotal.rmqestimator.rest.httpclient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.pivotal.rmqestimator.rest.HttpClientProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Component
@Scope("prototype")
public class BasicAuthHttpClientProvider implements HttpClientProvider {

	private String username;
	private String password;
	
	public BasicAuthHttpClientProvider(String username, String password) {
		
		this.username = username;
		this.password = password;
	}

    @Override
    public boolean useSsl() {

        return false;
    }

    @Override
	public Client getClient() {
		
		ClientConfig clientConf = new DefaultClientConfig();
		clientConf.getClasses().add(GsonMessageBodyHandler.class);
		
		Client client = Client.create(clientConf);
		
		client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
		
		return client;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
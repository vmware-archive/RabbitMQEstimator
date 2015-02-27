package io.pivotal.rmqestimator.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Component
@Scope("prototype")
public class HttpContext {

	private static final Logger logger = LoggerFactory.getLogger(HttpContext.class);
	
	Client client;
	String protocol = "http";
	String hostname;
	int port;
	Gson gson = new Gson();
	
	public HttpContext(
			Client client, String protocol,
			String hostname, int port) {
		
		this.protocol = protocol;
		this.hostname = hostname;
		this.port = port;
		this.client = client;
	}
	
	/**
	 * Compose the URL from the protocol://hostname:port + relativeApiPath
	 * @param relativeApiPath Management route to call
	 * @return URL to call
	 */
	String buildUrl(String relativeApiPath){
		
		return String.format("%s://%s:%s/api%s", this.protocol, this.hostname, this.port, relativeApiPath);
	}
	
	/**
	 * Compose a URI from the protocol://hostname:port + relativeApiPath.
	 * The only reason why this exists is to work around a URL Encoding issue
	 * in which Spring will encode "/" to "%225F" instead of "%2F".
	 * @param relativeApiPath Management route to call.
	 * @return URI to call
	 */
	URI buildUri(String relativeApiPath) {
		
		URI uri = null;
		
		try {
			
			uri = new URI(buildUrl(relativeApiPath));
			
		} catch (URISyntaxException e) {
			
			logger.error("Problem constructing URI: {}", e);
		}
		return uri;
	}
	
	/**
	 * Execute a GET call against the partial URL and deserialize the results.
	 * @param partialUrl Partial URL to build
	 * @param returnType Expected Return type.
	 * @return Your desired return type.
	 */
	public <T> Optional<T> GET(String partialUrl, GenericType<T> returnType){
		
		URI uri = buildUri(partialUrl);
		
		return makeGetRequest(uri, returnType);
	}

    /**
     * Execute a GET call against the partial URL and deserialize the results.
     * @param partialUrl Partial URL to build
     * @param returnType Expected Return type.
     * @param headers Headers to add to the request.
     * @return Your desired return type.
     */
    public <T> Optional<T> GET(String partialUrl, Map<String, Object> headers, GenericType<T> returnType){

        URI uri = buildUri(partialUrl);

        return makeGetRequest(uri, headers, returnType);
    }
	
	/**
	 * Execute a PUT call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 * @param payload Object to PUT
	 */
	public void PUT(String partialUrl, Object payload){
		
		URI uri = buildUri(partialUrl);
		
		makePutRequest(uri, payload);
	}

    /**
     * Execute a PUT call against the partial URL.
     * @param partialUrl Partial URL to build.
     * @param payload Object to PUT
     */
    public void PUT(String partialUrl, Map<String, Object> headers, Object payload){

        URI uri = buildUri(partialUrl);

        makePutRequest(uri, headers, payload);
    }

	/**
	 * Execute a POST call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 * @param payload Object to POST.
	 */
	public void POST(String partialUrl, Object payload){
		
		URI uri = buildUri(partialUrl);
		
		makePostRequest(uri, payload);
	}

    /**
     * Execute a POST call against the partial URL.
     * @param partialUrl Partial URL to build.
     * @param payload Object to POST.
     */
    public void POST(String partialUrl, Map<String, Object> headers, Object payload){

        URI uri = buildUri(partialUrl);

        makePostRequest(uri, headers, payload);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param partialUrl Partial URL to build.
     * @param payload Object to POST.
     * @param returnType Expected Return type.
     * @return Your desired return type.
     */
    public <T> Optional<T> POST(String partialUrl, Object payload, GenericType<T> returnType){

        URI uri = buildUri(partialUrl);

        return makePostRequest(uri, payload, returnType);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param partialUrl Partial URL to build.
     * @param payload Object to POST.
     * @param returnType Expected Return type.
     * @return Your desired return type.
     */
    public <T> Optional<T> POST(String partialUrl, Object payload, Map<String, Object> headers, GenericType<T> returnType){

        URI uri = buildUri(partialUrl);

        return makePostRequest(uri, payload, headers, returnType);
    }
	
	/**
	 * Execute a DELETE call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 */
	public void DELETE(String partialUrl){
		
		URI uri = buildUri(partialUrl);
		
		makeDeleteRequest(uri);
	}

    /**
     * Execute a DELETE call against the partial URL.
     * @param partialUrl Partial URL to build.
     */
    public void DELETE(String partialUrl, Map<String, Object> headers){

        URI uri = buildUri(partialUrl);

        makeDeleteRequest(uri, headers);
    }
	
	/**
	 * Execute a GET request and return the result.
	 * @param uri Raw URI to call.
	 * @param expectedReturnType Type to marshall the result back to.
	 * @return
	 */
	public <T> Optional<T> makeGetRequest(URI uri, GenericType<T> expectedReturnType){

		return makeGetRequest(uri, null, expectedReturnType);
	}

    /**
     * Execute a GET request and return the result.
     * @param uri Raw URI to call.
     * @param expectedReturnType Type to marshall the result back to.
     * @param headers Headers to add to the request.
     * @return
     */
    public <T> Optional<T> makeGetRequest(URI uri, Map<String, Object> headers, GenericType<T> expectedReturnType){

        WebResource webResource = this.client.resource(uri);

        applyHeaders(webResource, headers);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        logResponse(uri, response);

        return extractEntityFromResponse(response, expectedReturnType);
    }

	/**
	 * Execute a PUT request.
	 * @param uri Raw URI to call.
	 * @param obj Object to send.
	 */
	public void makePutRequest(URI uri, Object obj){
		
		makePutRequest(uri, null, obj);
	}

    /**
     * Execute a PUT request.
     * @param uri Raw URI to call.
     * @param obj Object to send.
     * @param headers Headers to add to the request.
     */
    public void makePutRequest(URI uri, Map<String, Object> headers, Object obj){

        WebResource webResource = this.client.resource(uri);

        applyHeaders(webResource, headers);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, obj);

        logResponse(uri, response);
    }
	
	/**
	 * Execute a POST request.
	 * @param uri Raw URI to call.
	 * @param obj Object to send.
	 */
	public void makePostRequest(URI uri, Object obj){
		
		makePostRequest(uri, null, obj);
	}

    /**
     * Execute a POST request.
     * @param uri Raw URI to call.
     * @param headers Headers to add to the request.
     * @param obj Object to send.
     */
    public void makePostRequest(URI uri, Map<String, Object> headers, Object obj){

        WebResource webResource = this.client.resource(uri);

        applyHeaders(webResource, headers);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, obj);

        logResponse(uri, response);
    }

    /**
     * Alternate form of POST allowing a return object.
     * @param uri Raw URI to call.
     * @param obj Object to send.
     * @param expectedReturnType Type to marshall the result back to.
     * @param <T>
     * @return
     */
    public <T> Optional<T> makePostRequest(URI uri, Object obj, GenericType<T> expectedReturnType){

        return makePostRequest(uri, obj, null, expectedReturnType);
    }

    /**
     * Alternate form of POST allowing a return object.
     * @param uri Raw URI to call.
     * @param obj Object to send.
     * @param headers Headers to add to the request.
     * @param expectedReturnType Type to marshall the result back to.
     * @param <T>
     * @return
     */
    public <T> Optional<T> makePostRequest(URI uri, Object obj, Map<String, Object> headers, GenericType<T> expectedReturnType){

        WebResource webResource = this.client.resource(uri);

        applyHeaders(webResource, headers);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, obj);

        logResponse(uri, response);

        return extractEntityFromResponse(response, expectedReturnType);
    }

	/**
	 * Execute a DELETE request.
	 * @param uri Raw URI to call.
	 */
	public void makeDeleteRequest(URI uri){
		
		makeDeleteRequest(uri, null);
	}

    /**
     * Execute a DELETE request.
     * @param uri Raw URI to call.
     * @param headers Headers to add to the request.
     */
    public void makeDeleteRequest(URI uri, Map<String, Object> headers){

        WebResource webResource = this.client.resource(uri);

        applyHeaders(webResource, headers);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

        logResponse(uri, response);
    }

    private <T> Optional<T> extractEntityFromResponse(ClientResponse response, GenericType<T> expectedReturnType){

        if (response.hasEntity() && response.getStatus() == 200)
            return Optional.of(response.getEntity(expectedReturnType));

        return Optional.absent();
    }

    private void applyHeaders(WebResource webResource, Map<String, Object> headers){

        if (headers != null) {

            for (Map.Entry<String, Object> e : headers.entrySet()) {

                webResource.header(e.getKey(), e.getValue());
            }
        }
    }

    private void logResponse(URI uri, ClientResponse response){

        logger.debug("{} => {}", uri.toString(), response.getStatus());

        if (response.getStatus() > 300)
            logger.warn(response.toString());
    }
}

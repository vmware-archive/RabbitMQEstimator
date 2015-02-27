package io.pivotal.rmqestimator.rest.mgmt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.*;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationLink;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import com.sun.jersey.api.client.GenericType;

public class BaseFluent {

    private static final Logger logger = LoggerFactory.getLogger(BaseFluent.class);
	private HttpContext HTTP;
	private RabbitNodeMgmtService mgmtService;
	/**
	 * Helps deserialization with Gson.
	 */
	static final GenericType<Exchange> EXCHANGE = new GenericType<Exchange>(){};
	static final GenericType<Collection<Exchange>> EXCHANGE_COLLECTION = new GenericType<Collection<Exchange>>(){};
	
	static final GenericType<Binding> BINDING = new GenericType<Binding>(){};
	static final GenericType<Collection<Binding>> BINDING_COLLECTION = new GenericType<Collection<Binding>>(){};
	
	static final GenericType<Queue> QUEUE = new GenericType<Queue>(){};
	static final GenericType<Collection<Queue>> QUEUE_COLLECTION = new GenericType<Collection<Queue>>(){};

    static final GenericType<PublishResponse> PUBLISH_RESPONSE = new GenericType<PublishResponse>(){};

    static final GenericType<ReceivedMessage> MESSAGE = new GenericType<ReceivedMessage>(){};
    static final GenericType<Collection<ReceivedMessage>> MESSAGE_COLLECTION = new GenericType<Collection<ReceivedMessage>>(){};

	static final GenericType<VirtualHost> VHOST = new GenericType<VirtualHost>(){};
	static final GenericType<Collection<VirtualHost>> VHOST_COLLECTION = new GenericType<Collection<VirtualHost>>(){};
	
	static final GenericType<Permission> PERMISSION = new GenericType<Permission>(){};
	static final GenericType<Collection<Permission>> PERMISSION_COLLECTION = new GenericType<Collection<Permission>>(){};
	
	static final GenericType<User> USER = new GenericType<User>(){};
	static final GenericType<Collection<User>> USER_COLLECTION = new GenericType<Collection<User>>(){};
	
	static final GenericType<Node> NODE = new GenericType<Node>(){};
	static final GenericType<Collection<Node>> NODE_COLLECTION = new GenericType<Collection<Node>>(){};

    static final GenericType<Parameter> PARAMETER = new GenericType<Parameter>(){};
    static final GenericType<Collection<Parameter>> PARAMETER_COLLECTION = new GenericType<Collection<Parameter>>(){};

    static final GenericType<Policy> POLICY = new GenericType<Policy>(){};
    static final GenericType<Collection<Policy>> POLICY_COLLECTION = new GenericType<Collection<Policy>>(){};

    static final GenericType<Connection> CONNECTION = new GenericType<Connection>(){};
    static final GenericType<Collection<Connection>> CONNECTION_COLLECTION = new GenericType<Collection<Connection>>(){};

    static final GenericType<FederationLink> FEDERATION_LINK = new GenericType<FederationLink>(){};
    static final GenericType<Collection<FederationLink>> FEDERATION_LINK_COLLECTION = new GenericType<Collection<FederationLink>>(){};
	

    public BaseFluent(HttpContext http, RabbitNodeMgmtService mgmtService) {
    	this.HTTP=http;
    	this.mgmtService=mgmtService;
    }
    /**
     * Virtual Hosts use "/" in their names.  So we need to
     * encode the "/" to it's URL encoded representation "%2F".
     * @param vhost Virtual Host
     * @return Encoded String
     */
    public static String encodeSlashes(String vhost){

        String encodedValue = vhost;

        try {

            encodedValue = URLEncoder.encode(vhost, "UTF-8");

        } catch (UnsupportedEncodingException e) {

            logger.error("Failed to encode value: {}", vhost);
        }

        return encodedValue;
    }


	/**
	 * @return the hTTP
	 */
	public HttpContext getHTTP() {
		return HTTP;
	}


	/**
	 * @param hTTP the hTTP to set
	 */
	public void setHTTP(HttpContext hTTP) {
		HTTP = hTTP;
	}


	/**
	 * @return the mgmtService
	 */
	public RabbitNodeMgmtService getMgmtService() {
		return mgmtService;
	}


	/**
	 * @param mgmtService the mgmtService to set
	 */
	public void setMgmtService(RabbitNodeMgmtService mgmtService) {
		this.mgmtService = mgmtService;
	}
    
    
    
}

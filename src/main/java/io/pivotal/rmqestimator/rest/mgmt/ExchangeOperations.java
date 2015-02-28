package io.pivotal.rmqestimator.rest.mgmt;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Binding;
import io.pivotal.rmqestimator.rest.mgmt.model.Exchange;
import io.pivotal.rmqestimator.rest.mgmt.model.Message;
import io.pivotal.rmqestimator.rest.mgmt.model.PublishResponse;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;


public class ExchangeOperations extends BaseFluent {
	/**
	 * Constructor
	 * @param http
	 * @param mgmtService
	 */
	public ExchangeOperations(HttpContext http,
			RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}

	private static final Logger logger = LoggerFactory.getLogger(ExchangeOperations.class);
/**
 * Get the mgmtSerice 
 * @return
 */
	public RabbitNodeMgmtService and() {
		return this.getMgmtService();
	}
	
	/**
	 * Get all exchanges on the broker (in the cluster) regardless
	 * of the virtual host.
	 * @return Collection of Exchanges
	 */
	public Collection<Exchange> all(){
		
		logger.debug("Getting all exchanges on every vhost.");
		
		return this.getHTTP().GET("/exchanges", EXCHANGE_COLLECTION).get();
	}
	
	/**
	 * Get all exchanges on the default vhost: "/".
	 * @return Collection of Exchanges
	 */
	public Collection<Exchange> allOnDefault(){
		
		logger.debug("Getting exchanges at from the default vhost.");
		
		return allOnVHost("/").get();
	}
	
	/**
	 * Get all exchanges on a specific virtual host.
	 * @param vhost Virtual Host with exchanges.
	 * @return Collection of Exchanges
	 */
	public Optional<Collection<Exchange>> allOnVHost(String vhost) {
		
		logger.debug("Getting exchanges at path: {}", vhost);
		
		return this.getHTTP().GET(
			String.format("/exchanges/%s", encodeSlashes(vhost)), EXCHANGE_COLLECTION);
	}
	
	/**
	 * Get an exchange from the default vhost by name.
	 * @param name Name of the exchange.
	 * @return the Exchange
	 */
	public Optional<Exchange> get(String name){
		
		return get("/", name);
	}
	
	/**
	 * Get an exchange from the specified vhost by name.
	 * @param vhost Virtual Host
	 * @param name Name of the exchange.
	 * @return the Exchange
	 */
	public Optional<Exchange> get(String vhost, String name){
		
		return this.getHTTP().GET(
			String.format("/exchanges/%s/%s", encodeSlashes(vhost), name), EXCHANGE);
	}
	
	
	public ExchangeOperations create(Exchange exchange){
		
		String url = String.format("/exchanges/%s/%s", encodeSlashes(exchange.getVhost()), exchange.getName());
		
		this.getHTTP().PUT(url, exchange);
		
		return this;
	}

    public ExchangeOperations publish(String exchangeName, Message message){

        return this.publish("/", exchangeName, message);
    }

    public ExchangeOperations publish(String vhost, String exchangeName, Message message){

        String url = String.format("/exchanges/%s/%s/publish", encodeSlashes(vhost), exchangeName);

        this.getHTTP().POST(url, message);

        return this;
    }

    public Optional<PublishResponse> publishAndConfirm(String exchangeName, Message message){

        return this.publishAndConfirm("/", exchangeName, message);
    }

    public Optional<PublishResponse> publishAndConfirm(String vhost, String exchangeName, Message message){

        String url = String.format("/exchanges/%s/%s/publish", encodeSlashes(vhost), exchangeName);

        return this.getHTTP().POST(url, message, PUBLISH_RESPONSE);
    }

    public ExchangeOperations delete(String name){

        return this.delete("/", name);
    }

	public ExchangeOperations delete(String vhost, String name){
		
		String url = String.format("/exchanges/%s/%s", encodeSlashes(vhost), name);
		
		this.getHTTP().DELETE(url);
		
		return this;
	}
	
	/**
	 * Get all bindings where consumers are getting messages FROM this exchange.
	 * @param exchangeName Name of the exchange.
	 * @return Collection of Bindings
	 */
	public Optional<Collection<Binding>> downstreamBindings(String exchangeName){
		
		return downstreamBindings("/", exchangeName);
	}
	
	/**
	 * Get all bindings where consumers are getting messages FROM this exchange.
	 * @param vhost Virtual Host
	 * @param exchangeName Name of the exchange.
	 * @return Collection of Bindings
	 */
	public Optional<Collection<Binding>> downstreamBindings(String vhost, String exchangeName){
		
		return getBindings(vhost, exchangeName, "source");
	}
	
	/**
	 * Get all bindings where producers are sending messages TO this exchange.
	 * @param exchangeName Name of the exchange.
	 * @return Collection of Bindings
	 */
	public Optional<Collection<Binding>> upstreamBindings(String exchangeName){
		
		return upstreamBindings("/", exchangeName);
	}
	
	/**
	 * Get all bindings where producers are sending messages TO this exchange.
	 * @param vhost Virtual Host
	 * @param exchangeName Name of the exchange.
	 * @return Collection of Bindings
	 */
	public Optional<Collection<Binding>> upstreamBindings(String vhost, String exchangeName){
		
		return getBindings(vhost, exchangeName, "destination");
	}
	
	/**
	 * Get bindings to this exchange.  
	 * @param vhost Virtual host of the exchange.
	 * @param exchangeName Name of the Exchange.
	 * @param direction Direction (source, destination)
	 * @return Collection of Bindings
	 */
    Optional<Collection<Binding>> getBindings(String vhost, String exchangeName, String direction){
		
		return this.getHTTP().GET(
			String.format("/exchanges/%s/%s/bindings/%s", 
				encodeSlashes(vhost), exchangeName, direction), BINDING_COLLECTION);
	}

}

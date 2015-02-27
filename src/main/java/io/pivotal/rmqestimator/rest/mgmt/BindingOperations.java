package io.pivotal.rmqestimator.rest.mgmt;

import com.google.common.base.Optional;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Binding;
import io.pivotal.rmqestimator.rest.mgmt.model.Exchange;
import io.pivotal.rmqestimator.rest.mgmt.model.Queue;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;
import java.util.Collection;

public class BindingOperations extends BaseFluent {
	
	public BindingOperations(HttpContext http, RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}

	public Optional<Collection<Binding>> all(){
		return this.getHTTP().GET("/bindings", BINDING_COLLECTION);
	}
	
	public Optional<Collection<Binding>> all(String vhost){
		
		return this.getHTTP().GET(String.format("/bindings/%s", encodeSlashes(vhost)), BINDING_COLLECTION);
	}
	
	public Optional<Collection<Binding>> getEtoQ(String vhost, String exchangeName, String queueName){

        return get(vhost, exchangeName, queueName, false);
	}

    public Optional<Collection<Binding>> getEtoE(String vhost, String exchangeName, String queueName){

        return get(vhost, exchangeName, queueName, true);
    }

    public Optional<Collection<Binding>> get(String vhost, String source, String destination, boolean isEtoE){

        return this.getHTTP().GET(
                String.format("/bindings/%s/e/%s/%s/%s",
                        encodeSlashes(vhost), source,
                        (isEtoE)? "e" : "q",
                        destination), BINDING_COLLECTION);
    }

    public Optional<Binding> getEtoQ(String vhost, String exchangeName, String queueName, String props){

        return get(vhost, exchangeName, queueName, false, props);
    }

    public Optional<Binding> getEtoE(String vhost, String exchangeName, String queueName, String props){

        return get(vhost, exchangeName, queueName, true, props);
    }

    public Optional<Binding> get(String vhost, String source, String destination, boolean isEtoE, String props){

        return this.getHTTP().GET(
                String.format("/bindings/%s/e/%s/q/%s/%s",
                        encodeSlashes(vhost), source,
                        (isEtoE)? "e" : "q",
                        destination, props), BINDING);
    }

    public BindingOperations create(Binding binding){

		String url = String.format(
			"/bindings/%s/e/%s/%s/%s",
                encodeSlashes(binding.getVhost()),
                binding.getSource(),
                binding.getDestinationType(),
                binding.getDestination());
		
		this.getHTTP().POST(url, binding);
		
		return this;
	}
	
	public BindingOperations create(Exchange exchange, Queue queue, String routingKey){

        Binding binding = new Binding(exchange, queue, routingKey);
		
		return this.create(binding);
	}
	
	public BindingOperations create(Exchange exchange, Exchange destExchange, String routingKey){
		
		Binding binding = new Binding(exchange, destExchange, routingKey);
		
		return this.create(binding);
	}

    public BindingOperations deleteEtoE(String source, String destination, String props){

        return delete("/", source, destination, true, props);
    }

    public BindingOperations deleteEtoE(String vhost, String source, String destination, String props){

        return delete(vhost, source, destination, true, props);
    }

    public BindingOperations deleteEtoQ(String source, String destination, String props){

        return delete("/", source, destination, false, props);
    }

    public BindingOperations deleteEtoQ(String vhost, String source, String destination, String props){

        return delete(vhost, source, destination, false, props);
    }

	public BindingOperations delete(String vhost, String source, String destination, boolean isEtoE, String props){
		
		this.getHTTP().DELETE(
			String.format("/bindings/%s/e/%s/%s/%s/%s",
                    encodeSlashes(vhost),
                    source,
                    (isEtoE)? "e" : "q",
                    destination, props));
		
		return this;
	}
}
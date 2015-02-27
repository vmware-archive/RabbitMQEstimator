package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents a binding between two Exchanges or an Exchange and a Queue.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Binding {
	
	protected String source;
	protected String vhost = "/";
	protected String destination;
	protected String destination_type = "q";
	protected String routing_key;
    @ProducedByRabbit
	protected String properties_key;
	protected Map<String, Object> arguments = Maps.newHashMap();
	
	public Binding(){}

    public Binding(String source, String destination, String routing_key){

        this.source = source;
        this.destination = destination;
        this.routing_key = routing_key;
    }

    public Binding(String source, String destination, boolean isExchange, String routing_key){

        this.source = source;
        this.destination = destination;
        this.routing_key = routing_key;
        this.destination_type = (isExchange)? "e" : "q";
    }

	public Binding(String source, String vhost, String destination,
			String destination_type, String routing_key, String properties_key,
			Map<String, Object> arguments) {

		this.source = source;
		this.vhost = vhost;
		this.destination = destination;
		this.destination_type = destination_type;
		this.routing_key = routing_key;
		this.properties_key = properties_key;
		this.arguments = arguments;
	}

    public Binding(Exchange source, Queue destination, String routingKey){

        this.source = source.getName();
        this.vhost = source.getVhost();
        this.destination = destination.getName();
        this.destination_type = "q";
        this.routing_key = routingKey;
    }

    public Binding(Exchange source, Queue destination, String routingKey, Map<String, Object> arguments){

        this.source = source.getName();
        this.vhost = source.getVhost();
        this.destination = destination.getName();
        this.destination_type = "q";
        this.routing_key = routingKey;
        this.arguments = arguments;
    }

    public Binding(Exchange source, Exchange destination, String routingKey){

        this.source = source.getName();
        this.vhost = source.getVhost();
        this.destination = destination.getName();
        this.destination_type = "e";
        this.routing_key = routingKey;
    }

    public Binding(Exchange source, Exchange destination, String routingKey, Map<String, Object> arguments){

        this.source = source.getName();
        this.vhost = source.getVhost();
        this.destination = destination.getName();
        this.destination_type = "e";
        this.routing_key = routingKey;
        this.arguments = arguments;
    }

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {

        this.source = source;
	}
	
	public String getVhost() {
		return vhost;
	}
	
	public void setVhost(String vhost) {

        this.vhost = vhost;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {

        this.destination = destination;
	}
	
	public String getDestinationType() {
		return destination_type;
	}
	
	public void setDestinationType(String destinationType) {

        this.destination_type = destinationType;
	}
	
	public String getRoutingKey() {
		return routing_key;
	}
	
	public void setRoutingKey(String routingKey) {

        this.routing_key = routingKey;
	}

    @ProducedByRabbit
	public String getPropertiesKey() {
		return properties_key;
	}
	
	public Map<String, Object> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, Object> arguments) {

        this.arguments = arguments;
	}

    public void setArgument(String key, Object value){ this.arguments.put(key, value); }
	
	@Override
	public String toString() {
		return "Binding [source=" + source + ", vhost=" + vhost
				+ ", destination=" + destination + ", destination_type="
				+ destination_type + ", routing_key=" + routing_key
				+ ", properties_key=" + properties_key + ", arguments="
				+ arguments + "]";
	}

    public boolean matches(Binding potentialMatch){

        return this.getSource().equals(potentialMatch.getSource())
                && this.getDestination().equals(potentialMatch.getDestination())
                && this.getDestinationType().equals(potentialMatch.getDestinationType())
                && this.getRoutingKey().equals(potentialMatch.getRoutingKey())
                && this.getArguments().equals(potentialMatch.getArguments());
    }

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        Binding binding = new Binding();

        public Builder fromExchange(String source){

            this.binding.setSource(source);

            return this;
        }

        public Builder toQueue(String destination){

            this.binding.setDestination(destination);

            this.binding.setDestinationType("q");

            return this;
        }

        public Builder toExchange(String destination){

            this.binding.setDestination(destination);

            this.binding.setDestinationType("e");

            return this;
        }

        public Builder vhost(String vhost){

            this.binding.setVhost(vhost);

            return this;
        }

        public Builder routingKey(String key){

            this.binding.setRoutingKey(key);

            return this;
        }

        public Builder arg(String key, Object value){

            this.binding.getArguments().put(key, value);

            return this;
        }

        public Builder arguments(Map<String, Object> arguments){

            this.binding.getArguments().putAll(arguments);

            return this;
        }

        public Binding build(){

            return binding;
        }
    }

}
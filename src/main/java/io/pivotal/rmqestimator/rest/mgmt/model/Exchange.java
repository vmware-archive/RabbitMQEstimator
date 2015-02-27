package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * An AMQP Exchange.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Exchange {
	
	protected String name;
	protected String vhost = "/";
	protected String type = "direct";
	protected boolean durable = false;
	protected boolean auto_delete = false;
	protected boolean internal = false;
	protected Map<String, Object> arguments = Maps.newHashMap();
	
	public Exchange(){}

    public Exchange(String name) {

        this.name = name;
    }

    public Exchange(String name, String type) {

        this.name = name;
        this.type = type;
    }

	public Exchange(String name, String vhost, String type) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
	}
	
	public Exchange(String name, String vhost, String type, boolean durable, boolean auto_delete) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
		this.durable = durable;
		this.auto_delete = auto_delete;
	}
	
	public Exchange(String name, String vhost, String type, boolean durable,
			boolean auto_delete, boolean internal, Map<String, Object> arguments) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
		this.durable = durable;
		this.auto_delete = auto_delete;
		this.internal = internal;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVhost() {
		return vhost;
	}
	
	public void setVhost(String vhost) {
		this.vhost = vhost;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isDurable() {
		return durable;
	}
	
	public void setDurable(boolean durable) {
		this.durable = durable;
	}
	
	public boolean isAutoDelete() {
		return auto_delete;
	}
	
	public void setAutoDelete(boolean shouldAutoDelete) {
		this.auto_delete = shouldAutoDelete;
	}
	
	public boolean isInternal() {
		return internal;
	}
	
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	
	public Map<String, Object> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, Object> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		return "Exchange [name=" + name + ", vhost=" + vhost + ", type=" + type
				+ ", durable=" + durable + ", auto_delete=" + auto_delete
				+ ", internal=" + internal + ", arguments=" + arguments + "]";
	}

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        Exchange exchange = new Exchange();

        public Builder name(String exchangeName){

            exchange.setName(exchangeName);

            return this;
        }

        public Builder vhost(String vhost){

            exchange.setVhost(vhost);

            return this;
        }

        public Builder direct(){

            return type("direct");
        }

        public Builder fanout(){

            return type("fanout");
        }

        public Builder topic(){

            return type("topic");
        }

        public Builder headers(){

            return type("headers");
        }

        public Builder type(String exchangeType){

            exchange.setType(exchangeType);

            return this;
        }

        public Builder durable(boolean trueIfDurable){

            exchange.setDurable(trueIfDurable);

            return this;
        }

        public Builder durable(){

            return durable(true);
        }

        public Builder autoDelete(boolean trueIfAutoDelete){

            exchange.setAutoDelete(trueIfAutoDelete);

            return this;
        }

        public Builder autoDelete(){

            return autoDelete(true);
        }

        public Builder internal(boolean trueIfInternal){

            exchange.setInternal(trueIfInternal);

            return this;
        }

        public Builder internal(){

            return internal(true);
        }

        public Builder arg(String key, Object value){

            exchange.getArguments().put(key, value);

            return this;
        }

        public Builder arguments(Map<String, Object> arguments){

            exchange.getArguments().putAll(arguments);

            return this;
        }

        public Exchange build(){ return exchange; }
    }
}
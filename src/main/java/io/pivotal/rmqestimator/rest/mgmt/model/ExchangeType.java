package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Represents a type of exchange that can be used on a Broker and whether it is enabled.
 * 
 * By default, RabbitMQ supports 4 types of exchanges: direct, topic, headers, fanout.
 * 
 * You can actually install others, like a JavaScript exchange.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class ExchangeType {

	protected String name;
	protected String description;
	protected boolean enabled;
	
	public ExchangeType(){}
	
	public ExchangeType(String name, String description, boolean enabled) {
		
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "ExchangeType [name=" + name + ", description=" + description
				+ ", enabled=" + enabled + "]";
	}
	
}

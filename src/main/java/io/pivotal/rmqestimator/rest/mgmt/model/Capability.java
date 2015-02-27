package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Describes a plugin or feature installed on the Broker.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Capability {

	protected String name;
	protected String description;
	protected boolean enabled;
	
	public Capability(){}
	
	public Capability(String name, String description, boolean enabled) {
		
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
		return "Capability [name=" + name + ", description=" + description
				+ ", enabled=" + enabled + "]";
	}
	
}

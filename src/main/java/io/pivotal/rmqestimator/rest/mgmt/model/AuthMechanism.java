package io.pivotal.rmqestimator.rest.mgmt.model;


public class AuthMechanism implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8000847154722375408L;
	protected String name;
	protected String description;
	protected boolean enabled = true;
	
	public AuthMechanism(){}
	
	public AuthMechanism(String name, String description, boolean enabled) {
		
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "AuthMechanism [name=" + name + ", description=" + description
				+ ", enabled=" + enabled + "]";
	}
	
}

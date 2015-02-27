package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * This is a wrapper around the RabbitMQ Status Route: /api/aliveness-test/{vhost}
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Status {

	protected String status;

	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return String.format("Status: %s", this.status);
	}
	
}

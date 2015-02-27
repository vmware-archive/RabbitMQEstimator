package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Global statistics of Queue usage in a RabbitMQ Cluster. 
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class QueueTotals {

	protected long messages;
	protected long messages_ready;
	protected long messages_unacknowledged;
	protected MessageDetails messages_details;
	protected MessageDetails messages_ready_details;
	protected MessageDetails messages_unacknowledged_details;
	
	public long getMessages() {
		return messages;
	}
	
	public long getMessagesReady() {
		return messages_ready;
	}
	
	public long getMessagesUnacknowledged() {
		return messages_unacknowledged;
	}
	
	public MessageDetails getMessagesDetails() {
		return messages_details;
	}
	
	public MessageDetails getMessagesReadyDetails() {
		return messages_ready_details;
	}
	
	public MessageDetails getMessagesUnacknowledgedDetails() {
		return messages_unacknowledged_details;
	}

	@Override
	public String toString() {
		return "QueueTotals [messages=" + messages + ", messages_ready="
				+ messages_ready + ", messages_unacknowledged="
				+ messages_unacknowledged + ", messages_details="
				+ messages_details + ", messages_ready_details="
				+ messages_ready_details + ", messages_unacknowledged_details="
				+ messages_unacknowledged_details + "]";
	}
}
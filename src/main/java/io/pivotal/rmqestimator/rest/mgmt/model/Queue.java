package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents an AMQP Queue.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Queue {

    @ProducedByRabbit
	protected long memory;
    @ProducedByRabbit
	protected String idle_since;
    @ProducedByRabbit
	protected String policy;
    @ProducedByRabbit
	protected String exclusive_consumer_tag;
    @ProducedByRabbit
	protected long messages_ready;
    @ProducedByRabbit
	protected long messages_unacknowledged;
    @ProducedByRabbit
	protected long messages;
    @ProducedByRabbit
	protected int consumers;
    @ProducedByRabbit
	protected int active_consumers;
    @ProducedByRabbit
	protected BackingQueueStatus backing_queue_status;
    @ProducedByRabbit
    protected String node;
    @ProducedByRabbit
    protected MessageDetails messages_details;
    @ProducedByRabbit
    protected MessageDetails messages_ready_details;
    @ProducedByRabbit
    protected MessageDetails messages_unacknowledged_details;

	protected String name;
	protected String vhost = "/";
	protected boolean durable = false;
	protected boolean auto_delete = false;
	protected Map<String, Object> arguments = Maps.newHashMap();
	
	public Queue(){}

    public Queue(String name) {

        this.name = name;
    }

	public Queue(String name, String vhost) {
		
		this.name = name;
		this.vhost = vhost;
	}
	
	public Queue(String name, String vhost, boolean durable, boolean auto_delete) {
		
		this.name = name;
		this.vhost = vhost;
		this.durable = durable;
		this.auto_delete = auto_delete;
	}
	
	public Queue(String name, String vhost, boolean durable,
			boolean auto_delete, Map<String, Object> arguments) {
		this.name = name;
		this.vhost = vhost;
		this.durable = durable;
		this.auto_delete = auto_delete;
		this.arguments = arguments;
	}

    @ProducedByRabbit
	public long getMemory() {
		return memory;
	}

    @ProducedByRabbit
	public String getIdleSince() {
		return idle_since;
	}
	
	public String getPolicy() {
		return policy;
	}
	
	public void setPolicy(String policy) {
		this.policy = policy;
	}

    @ProducedByRabbit
	public String getExclusiveConsumerTag() {
		return exclusive_consumer_tag;
	}

    @ProducedByRabbit
	public long getMessagesReady() {
		return messages_ready;
	}

    @ProducedByRabbit
	public long getMessagesUnacknowledged() {
		return messages_unacknowledged;
	}

    @ProducedByRabbit
	public long getMessages() {
		return messages;
	}

    @ProducedByRabbit
	public int getConsumers() {
		return consumers;
	}

    @ProducedByRabbit
	public int getActiveConsumers() {
		return active_consumers;
	}

    @ProducedByRabbit
    public BackingQueueStatus getBackingQueueStatus() {
		return backing_queue_status;
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
	
	public boolean isDurable() {
		return durable;
	}
	
	public void setDurable(boolean durable) {
		this.durable = durable;
	}
	
	public boolean isAutoDelete() {
		return auto_delete;
	}
	
	public void setAutoDelete(boolean autoDelete) {
		this.auto_delete = autoDelete;
	}
	
	public Map<String, Object> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, Object> arguments) {
		this.arguments = arguments;
	}

    @ProducedByRabbit
	public String getNode() {
		return node;
	}

    @ProducedByRabbit
	public MessageDetails getMessagesDetails() {
		return messages_details;
	}

    @ProducedByRabbit
	public MessageDetails getMessagesReadyDetails() {
		return messages_ready_details;
	}

    @ProducedByRabbit
	public MessageDetails getMessagesUnacknowledgedDetails() {
		return messages_unacknowledged_details;
	}

	@Override
	public String toString() {
		return "Queue [memory=" + memory + ", idle_since=" + idle_since
				+ ", policy=" + policy + ", exclusive_consumer_tag="
				+ exclusive_consumer_tag + ", messages_ready=" + messages_ready
				+ ", messages_unacknowledged=" + messages_unacknowledged
				+ ", messages=" + messages + ", consumers=" + consumers
				+ ", active_consumers=" + active_consumers
				+ ", backing_queue_status=" + backing_queue_status + ", name="
				+ name + ", vhost=" + vhost + ", durable=" + durable
				+ ", auto_delete=" + auto_delete + ", arguments=" + arguments
				+ ", node=" + node + ", messages_details=" + messages_details
				+ ", messages_ready_details=" + messages_ready_details
				+ ", messages_unacknowledged_details="
				+ messages_unacknowledged_details + "]";
	}

    public static Builder builder(){ return new Builder(); }


    public static class Builder {

        Queue queue = new Queue();

        public Builder name(String queueName){

            queue.setName(queueName);

            return this;
        }

        public Builder vhost(String vhost){

            queue.setVhost(vhost);

            return this;
        }

        public Builder durable(boolean trueIfDurable){

            queue.setDurable(trueIfDurable);

            return this;
        }

        public Builder durable(){

            return durable(true);
        }

        public Builder autoDelete(boolean trueIfAutoDelete){

            queue.setAutoDelete(trueIfAutoDelete);

            return this;
        }

        public Builder autoDelete(){

            return autoDelete(true);
        }

        public Builder arg(String key, Object value){

            queue.getArguments().put(key, value);

            return this;
        }

        public Builder arguments(Map<String, Object> arguments){

            queue.getArguments().putAll(arguments);

            return this;
        }

        public Queue build(){ return queue; }
    }
}
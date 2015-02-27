package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Statistics about the total number of topology objects (exchanges, queues,
 * consumers, connections and channels) on a RabbitMQ Cluster.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class TopologyObjectTotals {

	protected int consumers;
	protected int queues;
	protected int exchanges;
	protected int connections;
	protected int channels;
	
	public int getConsumers() {
		return consumers;
	}
	
	public int getQueues() {
		return queues;
	}
	
	public int getExchanges() {
		return exchanges;
	}
	
	public int getConnections() {
		return connections;
	}
	
	public int getChannels() {
		return channels;
	}
	
	@Override
	public String toString() {
		return "TopologyObjectTotals [consumers=" + consumers + ", queues="
				+ queues + ", exchanges=" + exchanges + ", connections="
				+ connections + ", channels=" + channels + "]";
	}	
}
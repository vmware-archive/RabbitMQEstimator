package io.pivotal.rmqestimator.rest.mgmt.model;

import java.util.Collection;

/**
 * Overview information for a single instance of the RabbitMQ Management Console
 * (i.e. not everything is cluster information).
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Overview {

	protected String management_version;
	protected String statistics_level;
	protected Collection<ExchangeType> exchange_types;
	protected String rabbitmq_version;
	protected String erlang_version;
	protected String node;
	protected String statistics_db_node;
	protected Collection<WebContext> contexts;
	protected Collection<ListenerContext> listeners;
	protected TopologyObjectTotals object_totals;
	protected QueueTotals queue_totals;
	
	public String getManagementConsoleVersion() {
		return management_version;
	}
	
	public String getStatisticsLevel() {
		return statistics_level;
	}
	
	public Collection<ExchangeType> getExchangeTypes() {
		return exchange_types;
	}
	
	public String getRabbitMQVersion() {
		return rabbitmq_version;
	}
	
	public String getErlangVersion() {
		return erlang_version;
	}
	
	public String getNode() {
		return node;
	}
	
	public String getStatisticsNode() {
		return statistics_db_node;
	}
	
	public Collection<WebContext> getContexts() {
		return contexts;
	}
	
	public Collection<ListenerContext> getListeners() {
		return listeners;
	}
	
	public TopologyObjectTotals getTopologyObjectTotals() {
		return object_totals;
	}
	
	public QueueTotals getQueueTotals() {
		return queue_totals;
	}
	
	@Override
	public String toString() {
		return "Overview [management_version=" + management_version
				+ ", statistics_level=" + statistics_level
				+ ", exchange_types=" + exchange_types + ", rabbitmq_version="
				+ rabbitmq_version + ", erlang_version=" + erlang_version
				+ ", node=" + node + ", statistics_db_node="
				+ statistics_db_node + ", contexts=" + contexts
				+ ", listeners=" + listeners + ", object_totals="
				+ object_totals + ", queue_totals=" + queue_totals + "]";
	}
}

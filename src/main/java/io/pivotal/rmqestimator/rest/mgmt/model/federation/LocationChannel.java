package io.pivotal.rmqestimator.rest.mgmt.model.federation;

public class LocationChannel implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3079890092157385623L;
	protected ConnectionDetails connection_details;
    protected String idle_since;
    protected boolean transactional;
    protected boolean confirm;
    protected int consumer_count;
    protected int messages_unacknowledged;
    protected int messages_unconfirmed;
    protected int messages_uncommitted;
    protected int acks_uncommitted;
    protected int prefetch_count;
    protected boolean client_flow_blocked;
    protected String node;
    protected String name;
    protected int number;
    protected String user;
    protected String vhost;

    public ConnectionDetails getConnectionDetails() {
        return connection_details;
    }

    public String getIdleSince() {
        return idle_since;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public int getConsumerCount() {
        return consumer_count;
    }

    public int getMessagesUnacknowledged() {
        return messages_unacknowledged;
    }

    public int getMessagesUnconfirmed() {
        return messages_unconfirmed;
    }

    public int getMessagesUncommitted() {
        return messages_uncommitted;
    }

    public int getAcksUncommitted() {
        return acks_uncommitted;
    }

    public int getPrefetchCount() {
        return prefetch_count;
    }

    public boolean isClientFlowBlocked() {
        return client_flow_blocked;
    }

    public String getNode() {
        return node;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getUser() {
        return user;
    }

    public String getVhost() {
        return vhost;
    }
}

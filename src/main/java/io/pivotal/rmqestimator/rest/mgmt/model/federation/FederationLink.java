package io.pivotal.rmqestimator.rest.mgmt.model.federation;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class FederationLink implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8203913431108218902L;
	protected String node;
    protected String status;
    protected String error;
    protected String local_connection;
    protected String type;
    protected String name;
    protected String upstream_name;
    protected String vhost;
    protected String connection;
    protected String uri;
    protected String timestamp;
    protected LocationChannel local_channel;

    public String getNode() {
        return node;
    }

    public String getStatus() {
        return status;
    }

    public String getError(){ return error; }

    public String getLocalConnection() {
        return local_connection;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUpstreamName() {
        return upstream_name;
    }

    public String getVirtualHost() {
        return vhost;
    }

    public String getConnection() {
        return connection;
    }

    public String getUri() {
        return uri;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LocationChannel getLocalChannel() {
        return local_channel;
    }
}

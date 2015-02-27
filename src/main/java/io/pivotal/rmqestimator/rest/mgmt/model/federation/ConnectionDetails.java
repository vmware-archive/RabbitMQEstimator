package io.pivotal.rmqestimator.rest.mgmt.model.federation;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class ConnectionDetails implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8757137011874618183L;
	protected String name;
    protected String peer_port;
    protected String peer_host;

    public String getName() {
        return name;
    }

    public String getPeerPort() {
        return peer_port;
    }

    public String getPeerHost() {
        return peer_host;
    }

}

package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Host, Port and Protocol a broker is accepting connections to. 
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class ListenerContext {

	protected String node;
	protected String protocol;
	protected String ip_address;
	protected int port;
	
	/**
	 * Hostname of the Node.
	 * @return Hostname
	 */
	public String getNode() {
		return node;
	}
	
	/**
	 * Protocol used: "amqp" or "amqp/ssl" 
	 * (I imagine if you are using MQTT or XMPP you will get something similar).
	 * @return Protocol used.
	 */
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * IP Address the Node is bound to.  This could be "::", meaning any
	 * device on the machine.
	 * @return IP Address or "::"
	 */
	public String getIPAddress() {
		return ip_address;
	}
	
	/**
	 * Physical port the broker is listening to for this protocol.
	 * @return Port number
	 */
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "ListenerContext [node=" + node + ", protocol=" + protocol
				+ ", ip_address=" + ip_address + ", port=" + port + "]";
	}
}
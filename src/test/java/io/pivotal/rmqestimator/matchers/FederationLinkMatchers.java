package io.pivotal.rmqestimator.matchers;

import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationLink;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class FederationLinkMatchers {

    public static NodeNameMatcher upstreamNode(String name){

        return new NodeNameMatcher(name);
    }

    public static UriMatcher upstreamUri(String uri){

        return new UriMatcher(uri);
    }

    public static ConnectionNameMatcher connectionName(String connectionName){

        return new ConnectionNameMatcher(connectionName);
    }

    public static TypeMatcher federationExType(){

        return new TypeMatcher("exchange");
    }

    public static TypeMatcher federationQType(){

        return new TypeMatcher("queue");
    }

    public static StatusMatcher status(String status){

        return new StatusMatcher(status);
    }

    public static StatusMatcher running(){

        return new StatusMatcher("running");
    }

    public static StatusMatcher inError(){

        return new StatusMatcher("error");
    }

    public static TopologyItemMatcher federatedExchange(String exchangeName){

        return new TopologyItemMatcher(exchangeName);
    }

    public static TopologyItemMatcher federatedQueue(String queueName){

        return new TopologyItemMatcher(queueName);
    }

    public static RemoteTopologyItemMatcher upstreamExchange(String exchangeName){

        return new RemoteTopologyItemMatcher(exchangeName);
    }

    public static RemoteTopologyItemMatcher upstreamQueue(String queueName){

        return new RemoteTopologyItemMatcher(queueName);
    }

    public static VHostMatcher upstreamVhost(String vhost){

        return new VHostMatcher(vhost);
    }

    public static class NodeNameMatcher implements FederationLinkMatcher {

        String nodeName;

        public NodeNameMatcher(String nodeName) {

            this.nodeName = nodeName;
        }

        @Override
        public boolean matches(FederationLink item) {

            return nodeName.equals(item.getNode());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Node '%s' does not match expected node '%s'.", item.getNode(), nodeName);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Node '%s' matches node for link.", nodeName);
        }
    }

    public static class UriMatcher implements FederationLinkMatcher {

        String uri;

        public UriMatcher(String uri) {

            this.uri = uri;
        }

        @Override
        public boolean matches(FederationLink item) {

            return uri.equals(item.getUri());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("URI '%s' does not match expected URI '%s'.", item.getUri(), uri);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("URI '%s' matches for link.", uri);
        }
    }

    public static class ConnectionNameMatcher implements FederationLinkMatcher {

        String connectionName;

        public ConnectionNameMatcher(String connectionName) {

            this.connectionName = connectionName;
        }

        @Override
        public boolean matches(FederationLink item) {
            return connectionName.equals(item.getConnection());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Connection id '%s' does not match expected id '%s'.", item.getConnection(), connectionName);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Connection id '%s' matches for link.", connectionName);
        }
    }

    public static class TypeMatcher implements FederationLinkMatcher {

        String type;

        public TypeMatcher(String type) {

            this.type = type;
        }

        @Override
        public boolean matches(FederationLink item) {
            return type.equals(item.getType());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Connection type '%s' does not match expected type '%s'.", item.getType(), type);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Connection type '%s' matches for link.", type);
        }
    }

    public static class StatusMatcher implements FederationLinkMatcher {

        String status;

        public StatusMatcher(String status) {

            this.status = status;
        }

        @Override
        public boolean matches(FederationLink item) {
            return status.equals(item.getType());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Connection status '%s' does not match expected status '%s'.", item.getStatus(), status);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Connection status '%s' matches for link.", status);
        }
    }

    public static class TopologyItemMatcher implements FederationLinkMatcher {

        String topologyItemName;

        public TopologyItemMatcher(String topologyItemName) {

            this.topologyItemName = topologyItemName;
        }

        @Override
        public boolean matches(FederationLink item) {
            return topologyItemName.equals(item.getName());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Topology item '%s' does not match expected name '%s'.", item.getName(), topologyItemName);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Topology item name '%s' matches for link.", topologyItemName);
        }
    }

    public static class RemoteTopologyItemMatcher implements FederationLinkMatcher {

        String topologyItemName;

        public RemoteTopologyItemMatcher(String topologyItemName) {

            this.topologyItemName = topologyItemName;
        }

        @Override
        public boolean matches(FederationLink item) {
            return topologyItemName.equals(item.getUpstreamName());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Remote topology item '%s' does not match expected name '%s'.", item.getUpstreamName(), topologyItemName);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Remote topology item name '%s' matches for link.", topologyItemName);
        }
    }

    public static class VHostMatcher implements FederationLinkMatcher {

        String vhost;

        public VHostMatcher(String vhost) {

            this.vhost = vhost;
        }

        @Override
        public boolean matches(FederationLink item) {
            return vhost.equals(item.getVirtualHost());
        }

        @Override
        public String getNotMatchReason(FederationLink item) {

            return String.format("Virtual host '%s' does not match expected vhost '%s'.", item.getVirtualHost(), vhost);
        }

        @Override
        public String getMatchReason(FederationLink item) {

            return String.format("Virtual host '%s' matches for link.", vhost);
        }
    }
}

package io.pivotal.rmqestimator.rest.mgmt.model;


public class Connection {

    int recv_oct;
    OctDetails recv_oct_details;
    int send_oct;
    OctDetails send_oct_details;
    int recv_cnt;
    int send_cnt;
    int send_pend;
    String state;
    String last_blocked_by;
    String last_blocked_age;
    int channels;
    String type;
    String node;
    String name;
    int port;
    int peer_port;
    String host;
    String peer_host;
    boolean ssl;
    String peer_cert_subject;
    String peer_cert_issuer;
    String peer_cert_validity;
    String auth_mechanism;
    String ssl_protocol;
    String ssl_key_exchange;
    String ssl_cipher;
    String protocol;
    String user;
    String vhost;
    int timeout;
    int frame_max;
    ClientProperties client_properties;

    public int getRecvOct() {
        return recv_oct;
    }

    public OctDetails getRecvOctDetails() {
        return recv_oct_details;
    }

    public int getSendOct() {
        return send_oct;
    }

    public OctDetails getSendOctDetails() {
        return send_oct_details;
    }

    public int getReceiveCount() {
        return recv_cnt;
    }

    public int getSendCount() {
        return send_cnt;
    }

    public int getSendPending() {
        return send_pend;
    }

    public String getState() {
        return state;
    }

    public String getLastBlockedBy() {
        return last_blocked_by;
    }

    public String getLastBlockedAge() {
        return last_blocked_age;
    }

    public int getChannels() {
        return channels;
    }

    public String getType() {
        return type;
    }

    public String getNode() {
        return node;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public int getPeerPort() {
        return peer_port;
    }

    public String getHost() {
        return host;
    }

    public String getPeerHost() {
        return peer_host;
    }

    public boolean isUsingSsl() {
        return ssl;
    }

    public String getPeerCertSubject() {
        return peer_cert_subject;
    }

    public String getPeerCertIssuer() {
        return peer_cert_issuer;
    }

    public String getPeerCertValidity() {
        return peer_cert_validity;
    }

    public String getAuthMechanism() {
        return auth_mechanism;
    }

    public String getSslProtocol() {
        return ssl_protocol;
    }

    public String getSslKeyExchange() {
        return ssl_key_exchange;
    }

    public String getSslCipher() {
        return ssl_cipher;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUser() {
        return user;
    }

    public String getVhost() {
        return vhost;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getFrameMax() {
        return frame_max;
    }

    public ClientProperties getClientProperties() {
        return client_properties;
    }

    public static class OctDetails {

        double rate;

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }

    public static class ClientProperties {

        String product;
        String information;
        String platform;
        String copyright;
        String version;
        ClientCapabilities capabilities;

        public String getProduct() {
            return product;
        }

        public String getInformation() {
            return information;
        }

        public String getPlatform() {
            return platform;
        }

        public String getCopyright() {
            return copyright;
        }

        public String getVersion() {
            return version;
        }

        public ClientCapabilities getCapabilities() {
            return capabilities;
        }
    }

    public static class ClientCapabilities {

        boolean exchange_exchange_bindings;
        boolean consumer_cancel_notify;
        boolean basic_nack;
        boolean publisher_confirms;

        public boolean canExchangeToExchangeBindings() {
            return exchange_exchange_bindings;
        }

        public boolean canConsumerCancelNotify() {
            return consumer_cancel_notify;
        }

        public boolean canBasicNack() {
            return basic_nack;
        }

        public boolean canPublisherConfirms() {
            return publisher_confirms;
        }
    }
}

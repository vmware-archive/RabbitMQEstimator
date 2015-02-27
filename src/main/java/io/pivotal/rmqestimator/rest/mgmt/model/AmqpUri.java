package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.base.Preconditions;


public class AmqpUri implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5885135091664945504L;
	protected String hostname;
    protected int port = -1;
    protected String vhost;
    protected String username;
    protected String password;
    protected boolean useSsl = false;
    protected String cacertfile;
    protected String certfile;
    protected String keyfile;
    protected String verify;
    protected Boolean failIfNoPeerCert;
    protected String authMechanism;

    public String getUri(){

        StringBuilder sb = new StringBuilder();

        sb.append("amqp");

        if (useSsl) sb.append("s");

        sb.append("://");

        if (null != username) sb.append(username).append(":").append(password).append("@");

        sb.append(hostname);

        if (-1 != port) sb.append(":").append(port);

        if (null != vhost) sb.append(vhost);

        if (useSsl){

            Preconditions.checkNotNull(cacertfile, "CA Certificate File required for SSL.");
            Preconditions.checkNotNull(certfile, "Certificate File required for SSL.");
            Preconditions.checkNotNull(keyfile, "Key File required for SSL.");

            sb
              .append("?")
              .append("cacertfile=").append(cacertfile)
              .append("&certfile=").append(certfile)
              .append("&keyfile=").append(keyfile);

            if (null != verify) sb.append("&verify=").append(verify);

            if (null != failIfNoPeerCert) sb.append("&fail_if_no_peer_cert=").append(failIfNoPeerCert);

            if (null != authMechanism) sb.append("&auth_mechanism=").append(authMechanism);
        }

        return sb.toString();
    }

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        AmqpUri uri = new AmqpUri();

        public Builder host(String hostname){

            uri.hostname = hostname;

            return this;
        }

        public Builder port(int port){

            uri.port = port;

            return this;
        }

        public Builder vhost(String vhost){

            String vhostPath = (vhost.startsWith("/"))? vhost : "/" + vhost;

            uri.vhost = vhostPath;

            return this;
        }

        public Builder credentials(String username, String password){

            uri.username = username;
            uri.password = password;

            return this;
        }

        public Builder useSsl(String cacertfile, String certfile, String keyfile){

            uri.useSsl = true;
            uri.cacertfile = cacertfile;
            uri.certfile = certfile;
            uri.keyfile = keyfile;

            return this;
        }

        public Builder requirePeerCertificate(){

            uri.failIfNoPeerCert = true;

            return this;
        }

        public Builder dontRequirePeerCertificate(){

            uri.failIfNoPeerCert = false;

            return this;
        }

        public Builder verifyCertificateIfPresent(){

            uri.verify = "verify";

            return this;
        }

        public Builder dontVerifyCertificateIfPresent(){

            uri.verify = "verify_none";

            return this;
        }

        public Builder authMechanism(String authMechanism){

            uri.authMechanism = authMechanism;

            return this;
        }

        public AmqpUri build(){ return uri; }

    }
}

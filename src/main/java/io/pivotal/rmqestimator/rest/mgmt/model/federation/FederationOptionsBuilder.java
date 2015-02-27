package io.pivotal.rmqestimator.rest.mgmt.model.federation;


public class FederationOptionsBuilder
        <T extends FederationOptionsBuilder, U extends FederationOptions> {

    U options;

    public FederationOptionsBuilder(U options) {

        this.options = options;
    }

    public T uri(String uri){

        options.setUri(uri);

        return self();
    }

    public T host(String host, int port){

        options.setUri(String.format("amqp://%s:%s", host, port));

        return self();
    }

    public T ackOnConfirm(){

        options.setAckMode("on-confirm");

        return self();
    }

    public T ackOnPublish(){

        options.setAckMode("on-publish");

        return self();
    }

    public T noAck(){

        options.setAckMode("no-ack");

        return self();
    }

    public T validateUpstreamUsers(){

        options.setTrustUserId(false);

        return self();
    }

    public T dontValidateUpstreamUsers(){

        options.setTrustUserId(true);

        return self();
    }

    public T self(){

        return (T) this;
    }

    public U build(){

        return options;
    }
}

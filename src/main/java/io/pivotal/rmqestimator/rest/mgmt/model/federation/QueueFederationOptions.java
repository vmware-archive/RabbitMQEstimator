package io.pivotal.rmqestimator.rest.mgmt.model.federation;

public class QueueFederationOptions extends FederationOptions {

    String queue;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public static Builder builder(){ return new Builder(); }

    public static class Builder extends FederationOptionsBuilder<Builder, QueueFederationOptions> {

        public Builder() { super(new QueueFederationOptions()); }

        public Builder queue(String queueName){

            options.setQueue(queueName);

            return this;
        }
    }
}

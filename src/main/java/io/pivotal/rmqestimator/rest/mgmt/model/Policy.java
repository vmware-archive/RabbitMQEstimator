package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class Policy extends BasePolicy {

    public static Builder builder(){ return new Builder(); }

    public static class Builder extends BasePolicy.Builder<Builder, Policy>{

        public Builder() { super(new Policy()); }
    }
}

package io.pivotal.rmqestimator.rest.mgmt.model.federation;

import io.pivotal.rmqestimator.rest.mgmt.model.BasePolicy;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class FederationPolicy extends BasePolicy {

    public FederationPolicy(){

        setUseAllUpstreamConnections();
    }

    public void setUseAllUpstreamConnections(){

        getDefinition().put("federation-upstream-set", "all");
    }

    public void setUpstreamConnectionSet(String upstreamSetName){

        getDefinition().put("federation-upstream-set", upstreamSetName);
    }

    public void setUpstreamConnection(String upstreamConnectionName){

        getDefinition().put("federation-upstream", upstreamConnectionName);
    }

    public static Builder builder(){  return new Builder(); }

    public static class Builder extends BasePolicy.Builder<Builder, FederationPolicy> {

        public Builder() { super(new FederationPolicy()); }

        public Builder upstream(String upstream){

            this.policy.getDefinition().put("federation-upstream", upstream);

            return self();
        }

        public Builder upstreamSet(String upstreamSetName){

            this.policy.getDefinition().put("federation-upstream-set", upstreamSetName);

            return self();
        }

        public Builder useAllUpstreams(){

            this.policy.getDefinition().put("federation-upstream-set", "all");

            return self();
        }
    }
}

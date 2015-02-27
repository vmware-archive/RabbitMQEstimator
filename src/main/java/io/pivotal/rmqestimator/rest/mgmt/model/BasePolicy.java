package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public abstract class BasePolicy {

    String pattern;

    Map<String, Object> definition = Maps.newHashMap();

    @SerializedName("apply-to")
    String apply_to = "exchanges";

    Integer priority;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<String, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<String, Object> definition) {
        this.definition = definition;
    }

    public String getApplyTo() {
        return apply_to;
    }

    public void setApplyTo(String applyTo) {
        this.apply_to = applyTo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public static abstract class Builder<DERIVED_BUILDER extends Builder, DERIVED_TYPE extends BasePolicy> {

        protected DERIVED_TYPE policy;

        public Builder(DERIVED_TYPE prototype){

            this.policy = prototype;
        }


        public DERIVED_BUILDER pattern(String pattern){

            policy.setPattern(pattern);

            return self();
        }

        public DERIVED_BUILDER appliesToExchanges(){

            policy.setApplyTo("exchanges");

            return self();
        }

        public DERIVED_BUILDER appliesToQueues(){

            policy.setApplyTo("queues");

            return self();
        }

        public DERIVED_BUILDER appliesToExchangesAndQueues(){

            policy.setApplyTo("all");

            return self();
        }

        public DERIVED_BUILDER priority(int priorityWeight){

            policy.setPriority(priorityWeight);

            return self();
        }

        public DERIVED_BUILDER setting(String key, Object value){

            policy.getDefinition().put(key, value);

            return self();
        }

        public DERIVED_BUILDER self(){

            return (DERIVED_BUILDER)this;
        }

        public DERIVED_TYPE build(){ return policy; }
    }
}

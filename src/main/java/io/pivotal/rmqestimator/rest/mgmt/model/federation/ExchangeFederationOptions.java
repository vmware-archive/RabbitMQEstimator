package io.pivotal.rmqestimator.rest.mgmt.model.federation;

import com.google.gson.annotations.SerializedName;


public class ExchangeFederationOptions extends FederationOptions {

    String exchange;

    @SerializedName("max-hops")
    Integer max_hops;

    Integer expires;

    @SerializedName("message-ttl")
    Integer message_ttl;

    @SerializedName("ha-policy")
    String ha_policy = "none";

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public int getMaxHops() {
        return max_hops;
    }

    public void setMaxHops(int max_hops) {
        this.max_hops = max_hops;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public int getMessageTtl() {
        return message_ttl;
    }

    public void setMessageTtl(int message_ttl) {
        this.message_ttl = message_ttl;
    }

    public String getHaPolicy() {
        return ha_policy;
    }

    public void setHaPolicy(String ha_policy) {
        this.ha_policy = ha_policy;
    }

    public static Builder builder(){

        return new Builder();
    }

    public static class Builder extends FederationOptionsBuilder<Builder, ExchangeFederationOptions> {

        public Builder() { super(new ExchangeFederationOptions()); }

        public Builder exchange(String exchangeName){

            options.setExchange(exchangeName);

            return this;
        }

        public Builder maxHops(int numberOfHops){

            options.setMaxHops(numberOfHops);

            return this;
        }

        public Builder expires(int milliseconds){

            options.setExpires(milliseconds);

            return this;
        }

        public Builder messageTTL(int milliseconds){

            options.setMessageTtl(milliseconds);

            return this;
        }

        public Builder haPolicy(String policy){

            options.setHaPolicy(policy);

            return this;
        }
    }
}

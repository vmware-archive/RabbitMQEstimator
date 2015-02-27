package io.pivotal.rmqestimator.rest.mgmt.model.federation;

import com.google.gson.annotations.SerializedName;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public abstract class FederationOptions {

    String uri;

    @SerializedName("prefetch-count")
    Integer prefetch_count;

    @SerializedName("reconnect-delay")
    Integer reconnect_delay;

    @SerializedName("ack-mode")
    String ack_mode = "on-confirm";

    @SerializedName("trust-user-id")
    Boolean trust_user_id;

    public void setUri(String uri) {

        this.uri = uri;
    }

    public void setPrefetchCount(int count) {

        this.prefetch_count = count;
    }

    public void setReconnectDelay(int delayInSeconds) {

        this.reconnect_delay = delayInSeconds;
    }

    public void setAckMode(String mode) {

        this.ack_mode = mode;
    }

    public void setTrustUserId(boolean trueIfShouldNotVerify) {

        this.trust_user_id = trueIfShouldNotVerify;
    }

    public String getUri() {

        return uri;
    }

    public int getPrefetchCount() {

        return prefetch_count;
    }

    public int getReconnectDelay() {

        return reconnect_delay;
    }

    public String getAckMode() {

        return ack_mode;
    }

    public boolean getShouldTrustUserId() {

        return trust_user_id;
    }

}

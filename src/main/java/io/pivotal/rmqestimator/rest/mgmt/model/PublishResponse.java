package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class PublishResponse {

    protected boolean routed = false;

    public boolean isRouted() {
        return routed;
    }

    @Override
    public String toString() {
        return "PublishResponse{" +
                "routed=" + routed +
                '}';
    }
}

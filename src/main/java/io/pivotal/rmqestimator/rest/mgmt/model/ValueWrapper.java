package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * Dumb class to correctly model a request body like this:
 *
 * {"value":{"uri":"amqp://server-name","expires":3600000}}
 *
 * @author Richard Clayton (Berico Technologies)
 */
public class ValueWrapper {

    Object value;

    public ValueWrapper(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}

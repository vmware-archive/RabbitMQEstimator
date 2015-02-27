package io.pivotal.rmqestimator.matchers;

import io.pivotal.rmqestimator.rest.mgmt.model.Message;
import io.pivotal.rmqestimator.rest.mgmt.model.ReceivedMessage;

import java.util.UUID;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class MessageMatchers {

    public static StringBodyMatcher body(String body){

        return new StringBodyMatcher(body);
    }

    public static ByteBodyMatcher body(byte[] body){

        return new ByteBodyMatcher(body);
    }

    public static HeaderMatcher header(String key, Object value){

        return new HeaderMatcher(key, value);
    }

    public static PropertyMatcher property(String key, Object value){

        return new PropertyMatcher(key, value);
    }

    public static RouteMatcher route(String routingKey){

        return new RouteMatcher(routingKey);
    }

    public static MarkerMatcher marked(Message message){

        return new MarkerMatcher(message);
    }

    public static class StringBodyMatcher implements MessageMatcher {

        String expectedBody;

        public StringBodyMatcher(String expectedBody) {
            this.expectedBody = expectedBody;
        }

        @Override
        public boolean matches(ReceivedMessage message) {
            return expectedBody.equals(message.getPayload());
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            return String.format("Message should have body '%s' but instead had '%s'.",
                    expectedBody, item.getPayload());
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message has body of '%s'.", expectedBody);
        }
    }

    public static class ByteBodyMatcher implements MessageMatcher {

        byte[] expectedBody;

        public ByteBodyMatcher(byte[] expectedBody) {
            this.expectedBody = expectedBody;
        }

        @Override
        public boolean matches(ReceivedMessage message) {
            return expectedBody.equals(message.getPayloadBytes());
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            return String.format("Message body bytes does not match (exp.len = %s, act.len = %s).",
                    expectedBody.length, item.getPayloadBytes().length);
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message body bytes matches (len = %s)", expectedBody.length);
        }
    }

    public static class HeaderMatcher implements MessageMatcher {

        String key;

        Object value;

        public HeaderMatcher(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean matches(ReceivedMessage message) {

            if (message.getHeaders().containsKey(key))
                return message.getHeaders().get(key).equals(value);

            return false;
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            if (!item.getHeaders().containsKey(key))
                return String.format("Message does not contain header: %s", key);

            return String.format("Message header '%s' should have value '%s' but has '%s'.",
                    key, value, item.getHeaders().get(key));
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message header '%s' was found with value '%s'.", key, value);
        }
    }

    public static class PropertyMatcher implements MessageMatcher {

        String key;

        Object value;

        public PropertyMatcher(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean matches(ReceivedMessage message) {

            if (message.getProperties().containsKey(key))
                return message.getProperties().get(key).equals(value);

            return false;
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            if (!item.getProperties().containsKey(key))
                return String.format("Message does not contain property: %s", key);

            return String.format("Message property '%s' should have value '%s' but has '%s'.",
                    key, value, item.getProperties().get(key));
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message property '%s' was found with value '%s'.", key, value);
        }
    }

    public static class RouteMatcher implements MessageMatcher {

        String routingKey;

        public RouteMatcher(String routingKey) {
            this.routingKey = routingKey;
        }

        @Override
        public boolean matches(ReceivedMessage message) {

            return routingKey.equals(message.getRoutingKey());
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            return String.format("Message routing key should be '%s' but is '%s'.", routingKey, item.getRoutingKey());
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message routing key was '%s'.", routingKey);
        }
    }

    public static class MarkerMatcher implements MessageMatcher {

        private static final String MARKER_KEY = "__eqMarker__";

        Message message;

        public MarkerMatcher(Message message) {

            // Apply a marker to the message.
            message.getHeaders().put(MARKER_KEY, UUID.randomUUID().toString());

            this.message = message;
        }

        @Override
        public boolean matches(ReceivedMessage item) {

            if (!item.getHeaders().containsKey(MARKER_KEY)) return false;

            return item.getHeaders().get(MARKER_KEY).equals(message.getHeaders().get(MARKER_KEY));
        }

        @Override
        public String getNotMatchReason(ReceivedMessage item) {

            if (!item.getHeaders().containsKey(MARKER_KEY))
                return "Message does not contain the message header marker.";

            return String.format("Message should have marker '%s' but instead has '%s'.",
                    message.getHeaders().get(MARKER_KEY),
                    item.getHeaders().get(MARKER_KEY));
        }

        @Override
        public String getMatchReason(ReceivedMessage item) {

            return String.format("Message had matching marker value of '%s'.", item.getHeaders().get(MARKER_KEY));
        }
    }
}

package io.pivotal.rmqestimator.matchers;

import io.pivotal.rmqestimator.rest.mgmt.model.Binding;


/**
 * @author Richard Clayton (Berico Technologies)
 */
public class BindingMatchers {

    public static SourceMatcher source(String exchangeName){

        return new SourceMatcher(exchangeName);
    }

    public static DestinationMatcher destination(String exchangeOrQueueName){

        return new DestinationMatcher(exchangeOrQueueName);
    }

    public static DestinationTypeMatcher isExToQ(){

        return new DestinationTypeMatcher("queue");
    }

    public static DestinationTypeMatcher isExToEx(){

        return new DestinationTypeMatcher("exchange");
    }

    public static RouteMatcher routingKey(String routingKey){

        return new RouteMatcher(routingKey);
    }


    public static class SourceMatcher implements BindingMatcher {

        private String source;

        public SourceMatcher(String source) {
            this.source = source;
        }

        @Override
        public boolean matches(Binding binding) {
            return source.equals(binding.getSource());
        }

        @Override
        public String getNotMatchReason(Binding item) {

            return String.format("Binding should have a source '%s' but actually has '%s'.",
                    source, item.getSource());
        }

        @Override
        public String getMatchReason(Binding item) {

            return String.format("Binding has source value of '%s'.", source);
        }
    }

    public static class DestinationMatcher implements BindingMatcher {

        private String destination;

        public DestinationMatcher(String destination) {
            this.destination = destination;
        }

        @Override
        public boolean matches(Binding binding) {
            return destination.equals(binding.getDestination());
        }

        @Override
        public String getNotMatchReason(Binding item) {

            return String.format("Binding should have destination '%s' but actually has '%s'.",
                    destination, item.getDestination());
        }

        @Override
        public String getMatchReason(Binding item) {

            return String.format("Binding has destination value of '%s'", destination);
        }
    }

    public static class DestinationTypeMatcher implements BindingMatcher {

        private String destinationType;

        public DestinationTypeMatcher(String destinationType) {
            this.destinationType = destinationType;
        }

        @Override
        public boolean matches(Binding binding) {
            return destinationType.equals(binding.getDestinationType());
        }

        @Override
        public String getNotMatchReason(Binding item) {

            return String.format(
                    "Binding should have destination type of '%s' but actually has '%s' (e=exchange, q=queue).",
                    destinationType, item.getDestinationType());
        }

        @Override
        public String getMatchReason(Binding item) {

            return String.format("Binding has destination type of '%s'.", destinationType);
        }
    }

    public static class RouteMatcher implements BindingMatcher {

        String routingKey;

        public RouteMatcher(String routingKey) {
            this.routingKey = routingKey;
        }

        @Override
        public boolean matches(Binding binding) {

            return routingKey.equals(binding.getRoutingKey());
        }

        @Override
        public String getNotMatchReason(Binding item) {

            return String.format("Binding should have routing key '%s' but actually has '%s'.",
                    routingKey, item.getRoutingKey());
        }

        @Override
        public String getMatchReason(Binding item) {

            return String.format("Binding has routing key value of '%s'.", routingKey);
        }
    }
}

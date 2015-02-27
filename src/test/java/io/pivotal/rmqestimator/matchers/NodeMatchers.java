package io.pivotal.rmqestimator.matchers;

import io.pivotal.rmqestimator.rest.mgmt.model.Node;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class NodeMatchers {

    public static NodeTypeMatcher isDiscNode(){

        return new NodeTypeMatcher("disc");
    }

    public static NodeTypeMatcher isRamNode(){

        return new NodeTypeMatcher("ram");
    }

    public static RunningMatcher isRunning(){

        return new RunningMatcher(true);
    }

    public static RunningMatcher isNotRunning(){

        return new RunningMatcher(false);
    }

    public static class NodeTypeMatcher implements NodeMatcher {

        String nodeType;

        public NodeTypeMatcher(String nodeType) {
            this.nodeType = nodeType;
        }

        @Override
        public boolean matches(Node item) {
            return nodeType.equals(item.getType());
        }

        @Override
        public String getNotMatchReason(Node item) {
            return String.format("Node '%s' is of type '%s' and not expected type of '%s'.",
                    item.getName(), item.getType(), nodeType);
        }

        @Override
        public String getMatchReason(Node item) {

            return String.format("Node '%s' is of type '%s'.", item.getName(), nodeType);
        }
    }

    public static class RunningMatcher implements NodeMatcher {

        boolean shouldBeRunning;

        public RunningMatcher(boolean shouldBeRunning) {
            this.shouldBeRunning = shouldBeRunning;
        }

        @Override
        public boolean matches(Node item) {
            return item.isRunning() == shouldBeRunning;
        }

        @Override
        public String getNotMatchReason(Node item) {

            return String.format("Node '%s' running status should be '%s' but is not.",
                    item.getName(), shouldBeRunning);
        }

        @Override
        public String getMatchReason(Node item) {

            return String.format("Node '%s' has running status of '%s'.", item.getName(), shouldBeRunning);
        }
    }
}

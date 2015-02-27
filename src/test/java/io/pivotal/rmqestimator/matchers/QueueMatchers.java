package io.pivotal.rmqestimator.matchers;

import io.pivotal.rmqestimator.rest.mgmt.model.Queue;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class QueueMatchers {

    public static CountMatcher count(int numMessages){

        return new CountMatcher(numMessages);
    }

    public static DurableMatcher isQDurable(){

        return new DurableMatcher(true);
    }

    public static DurableMatcher isNotQDurable(){

        return new DurableMatcher(false);
    }

    public static AutoDeleteMatcher isQAutoDelete(){

        return new AutoDeleteMatcher(true);
    }

    public static AutoDeleteMatcher isNotQAutoDelete(){

        return new AutoDeleteMatcher(false);
    }

    public static class CountMatcher implements QueueMatcher {

        private int count;

        public CountMatcher(int count) {
            this.count = count;
        }

        @Override
        public boolean matches(Queue queue) {
            return count == queue.getMessages();
        }

        @Override
        public String getNotMatchReason(Queue item) {

            return String.format("Queue '%s' should have '%s' messages but has '%s'.",
                    item.getName(), count, item.getMessages());
        }

        @Override
        public String getMatchReason(Queue item) {

            return String.format("Queue '%s' has '%s' messages.", item.getName(), count);
        }
    }

    public static class DurableMatcher implements QueueMatcher {

        boolean shouldBeDurable;

        public DurableMatcher(boolean shouldBeDurable) {
            this.shouldBeDurable = shouldBeDurable;
        }

        @Override
        public boolean matches(Queue queue) {

            return queue.isDurable() == shouldBeDurable;
        }

        @Override
        public String getNotMatchReason(Queue item) {

            return String.format("Queue '%s' durable status should be '%s' but is not.",
                    item.getName(), shouldBeDurable);
        }

        @Override
        public String getMatchReason(Queue item) {

            return String.format("Queue '%s' has durable status of '%s'.", item.getName(), shouldBeDurable);
        }
    }

    public static class AutoDeleteMatcher implements QueueMatcher {

        boolean shouldBeAutoDelete;

        public AutoDeleteMatcher(boolean shouldBeAutoDelete) {
            this.shouldBeAutoDelete = shouldBeAutoDelete;
        }

        @Override
        public boolean matches(Queue queue) {

            return queue.isAutoDelete() == shouldBeAutoDelete;
        }

        @Override
        public String getNotMatchReason(Queue item) {

            return String.format("Queue '%s' autodelete status should be '%s' but is not.",
                    item.getName(), shouldBeAutoDelete);
        }

        @Override
        public String getMatchReason(Queue item) {

            return String.format("Queue '%s' has autodelete status of '%s'.", item.getName(), shouldBeAutoDelete);
        }
    }
}

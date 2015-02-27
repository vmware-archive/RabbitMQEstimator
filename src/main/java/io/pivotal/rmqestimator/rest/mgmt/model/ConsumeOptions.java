package io.pivotal.rmqestimator.rest.mgmt.model;

/**
 * count controls the maximum number of messages to get. You may get fewer messages than this if the queue cannot immediately provide them.
 requeue determines whether the messages will be removed from the queue. If requeue is true they will be requeued - but their redelivered flag will be set.
 encoding must be either "auto" (in which case the payload will be returned as a string if it is valid UTF-8, and base64 encoded otherwise), or "base64" (in which case the payload will always be base64 encoded).
 If truncate is present it will truncate the message payload if it is larger than the size given (in bytes).
 truncate is optional; all other keys are mandatory.
 *
 * @author Richard Clayton (Berico Technologies)
 */
public class ConsumeOptions {

    // {"count":5,"requeue":true,"encoding":"auto","truncate":50000}
    protected int count = 1;
    protected boolean requeue = true;
    protected String encoding = "auto";
    protected int truncate = 50000;

    public int getCount() {
        return count;
    }

    public boolean isRequeue() {
        return requeue;
    }

    public String getEncoding() {
        return encoding;
    }

    public int getTruncate() {
        return truncate;
    }

    public static ConsumeOptionsBuilder builder(){

        return new ConsumeOptionsBuilder();
    }

    public static class ConsumeOptionsBuilder {

        ConsumeOptions options = new ConsumeOptions();

        public ConsumeOptionsBuilder retrieveAtMost(int number){

            options.count = number;

            return this;
        }

        public ConsumeOptionsBuilder requeueMessage(boolean shouldRequeue){

            options.requeue = shouldRequeue;

            return this;
        }

        public ConsumeOptionsBuilder withStringEncoding(){

            options.encoding = "string";

            return this;
        }

        public ConsumeOptionsBuilder withBase64Encoding(){

            options.encoding = "base64";

            return this;
        }

        public ConsumeOptionsBuilder truncatePayloadAt(int numBytes){

            options.truncate = numBytes;

            return this;
        }

        public ConsumeOptions build(){ return options; }

    }
}

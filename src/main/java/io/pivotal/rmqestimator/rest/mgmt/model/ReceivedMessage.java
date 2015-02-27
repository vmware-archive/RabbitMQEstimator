package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

public class ReceivedMessage {

    static Gson gson = new Gson();

    protected String routing_key;
    protected String payload;
    protected String payload_encoding;
    protected int payload_bytes;
    protected boolean redelivered = false;
    protected String exchange;
    protected int message_count;
    protected Map<String, Object> properties;

    public int getPayloadTotalBytes() {
        return payload_bytes;
    }

    public boolean isRedelivered() {
        return redelivered;
    }

    public String getExchange() {
        return exchange;
    }

    public int getMessageCount() {
        return message_count;
    }

    public String getRoutingKey() { return routing_key; }

    public String getPayload() { return payload; }

    public String getPayloadEncoding() { return payload_encoding; }

    public Map<String, Object> getProperties() { return properties; }

    public byte[] getPayloadBytes(){

        return Base64.decodeBase64(this.payload);
    }

    public <T> Optional<T> getPayloadEntity(Class<T> typeToConvertTo){

        if (payload_encoding.equals("string"))
            return Optional.of(gson.fromJson(payload, typeToConvertTo));
        else
            return Optional.absent();
    }

    public Map<String, Object> getHeaders() {

        if (properties.containsKey("headers")) return (Map<String, Object>)properties.get("headers");

        return new HashMap<String, Object>();
    }

    @Override
    public String toString() {
        return "ReceivedMessage{" +
                "payload_bytes=" + payload_bytes +
                ", redelivered=" + redelivered +
                ", exchange='" + exchange + '\'' +
                ", routing_key='" + routing_key + '\'' +
                ", message_count=" + message_count +
                ", payload='" + payload + '\'' +
                ", payload_encoding='" + payload_encoding + '\'' +
                ", properties=" + properties +
                '}';
    }
}

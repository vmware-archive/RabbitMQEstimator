package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class Message {

    static Gson gson = new Gson();

    protected String routing_key;
    protected String payload;
    protected String payload_encoding;
    protected Map<String, Object> properties = Maps.newHashMap();

    public Message(){

        properties.put("headers", new HashMap<String, Object>());
    }

    public String getRoutingKey() {
        return routing_key;
    }

    public String getPayload() {
        return payload;
    }

    public String getPayloadEncoding() {
        return payload_encoding;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public Message setRoutingKey(String routing_key){

        this.routing_key = routing_key;

        return this;
    }

    public Message setHeader(String key, Object value){

        getHeaders().put(key, value);

        return this;
    }

    public Message setProperty(String key, Object value){

        properties.put(key, value);

        return this;
    }

    public Message setPayload(String payload){

        this.payload = payload;
        this.payload_encoding = "string";

        return this;
    }

    public Message setPayloadEntity(Object payload){

        this.payload = gson.toJson(payload);
        this.payload_encoding = "string";

        return this;
    }

    public Message setPayloadBytes(byte[] bytes){

        this.payload = Base64.encodeBase64String(bytes);
        this.payload_encoding = "base64";

        return this;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getHeaders(){

        return (Map<String, Object>)this.properties.get("headers");
    }

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        Message message = new Message();

        public Builder routingKey(String key){

            message.setRoutingKey(key);

            return this;
        }

        public Builder header(String key, Object value){

            message.setHeader(key, value);

            return this;
        }

        public Builder property(String key, Object value){

            message.setProperty(key, value);

            return this;
        }

        public Builder payload(String payload){

            message.setPayload(payload);

            return this;
        }

        public Builder payload(byte[] payload){

            message.setPayloadBytes(payload);

            return this;
        }

        public Builder entity(Object entity){

            message.setPayloadEntity(entity);

            return this;
        }

        public Message build(){ return message; }
    }
}

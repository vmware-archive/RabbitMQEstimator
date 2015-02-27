package io.pivotal.rmqestimator.rest.mgmt.model;



/**
 * @author Richard Clayton (Berico Technologies)
 */
public class Parameter<T> {

    T value;
    String vhost;
    String component;
    String name;

    public T getValue() {

        return value;
    }

    public String getVhost() {
        return vhost;
    }

    public String getComponent() {
        return component;
    }

    public String getName() {
        return name;
    }

    public void setValue(T value) {

        this.value = value;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        Parameters.ObjectParameter parameter = new Parameters.ObjectParameter();

        public Builder name(String parameterName){

            parameter.setName(parameterName);

            return this;
        }

        public Builder vhost(String vhost){

            parameter.setVhost(vhost);

            return this;
        }

        public Builder component(String component){

            parameter.setComponent(component);

            return this;
        }

        public Builder value(Object value){

            parameter.setValue(value);

            return this;
        }

        public Parameter build(){

            return parameter;
        }
    }
}

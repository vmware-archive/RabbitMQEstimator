package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.base.Joiner;

/**
 * RabbitMQ Permissions schema.  Please remember that 
 * EVERY property, EXCEPT "user" and "vhost", is a 
 * Regular Expression!
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Permission {

    private static final String ALL = ".*";
    private static final String NONE = "^$";

	protected String vhost = "/";
	protected String user;
	protected String configure = ALL;
	protected String write = ALL;
	protected String read = ALL;
	
	public Permission(){}

    public Permission(String user, String configure,  String write, String read) {
        this.user = user;
        this.configure = configure;
        this.write = write;
        this.read = read;
    }

	public Permission(String vhost, String user, String configure, String write, String read) {
		this.vhost = vhost;
		this.user = user;
		this.configure = configure;
		this.write = write;
		this.read = read;
	}
	
	public String getVhost() {
		return vhost;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getConfigure() {
		return configure;
	}
	
	public String getWrite() {
		return write;
	}
	
	public String getRead() {
		return read;
	}
	
	public void setVhost(String vhost) {

		this.vhost = vhost;
	}

	public void setUser(String user) {

        this.user = user;
	}

	public void setConfigure(String configure) {

		this.configure = configure;
	}

	public void setWrite(String write) {

        this.write = write;
	}

	public void setRead(String read) {

        this.read = read;
	}

	@Override
	public String toString() {
		return "Permission [vhost=" + vhost + ", user=" + user + ", configure="
				+ configure + ", write=" + write + ", read=" + read + "]";
	}

    public static Builder builder(){ return new Builder(); }


    public static class Builder {

        Permission permission = new Permission();

        public Builder user(String username){

            permission.setUser(username);

            return this;
        }

        public Builder vhost(String vhost){

            permission.setVhost(vhost);

            return this;
        }

        public Builder read(String readExpression){

            permission.setRead(readExpression);

            return this;
        }

        public Builder readAll(){

            return read(ALL);
        }

        public Builder readOnly(String... queues){

            return read(Joiner.on("|").join(queues));
        }

        public Builder readNone(String... queues){

            return read(NONE);
        }

        public Builder write(String writeExpression){

            permission.setWrite(writeExpression);

            return this;
        }

        public Builder writeAll(){

            return write(ALL);
        }

        public Builder writeOnly(String... exchanges){

            return write(Joiner.on("|").join(exchanges));
        }

        public Builder writeNone(){

            return write(NONE);
        }

        public Builder configure(String configureExpression){

            permission.setConfigure(configureExpression);

            return this;
        }

        public Builder configureAll(){

            return configure(ALL);
        }

        public Builder configureOnly(String... exchangesAndQueues){

            return configure(Joiner.on("|").join(exchangesAndQueues));
        }

        public Builder configureNone(){

            return configure(NONE);
        }

        public Builder fullPermissions(){

            readAll();
            writeAll();
            return configureAll();
        }

        public Builder noPermissions(){

            readNone();
            writeNone();
            return configureNone();
        }

        public Permission build(){ return permission; }
    }
}

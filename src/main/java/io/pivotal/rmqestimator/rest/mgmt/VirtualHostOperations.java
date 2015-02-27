package io.pivotal.rmqestimator.rest.mgmt;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Permission;
import io.pivotal.rmqestimator.rest.mgmt.model.Status;
import io.pivotal.rmqestimator.rest.mgmt.model.VirtualHost;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;

import com.google.common.base.Optional;
import com.sun.jersey.api.client.GenericType;

public class VirtualHostOperations extends BaseFluent {

	public VirtualHostOperations(HttpContext http,
			RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}
	
	public Collection<VirtualHost> all(){
		
		return this.getHTTP().GET("/vhosts", VHOST_COLLECTION).get();
	}
	
	public Optional<VirtualHost> get(String vhost){
		
		return this.getHTTP().GET(String.format("/vhosts/%s", encodeSlashes(vhost)), VHOST);
    }


	public VirtualHostOperations delete(String vhost){
		
		this.getHTTP().DELETE(String.format("/vhosts/%s", encodeSlashes(vhost)));
		
		return this;
	}
	
	public VirtualHostOperations create(VirtualHost vhost){
		
		String url = String.format("/vhosts/%s", encodeSlashes(vhost.getName()));
		
		this.getHTTP().PUT(url, vhost);
		
		return this;
	}
	
	public Status status(){
		
		return this.status("/").get();
	}
	
	public Optional<Status> status(String vhost){
		
		return this.getHTTP().GET(
			String.format("/aliveness-test/%s", encodeSlashes(vhost)), new GenericType<Status>(){});
	}
	
	public Collection<Permission> permissions(){
		
		return permissions("/").get();
	}
	
	public Optional<Collection<Permission>> permissions(String vhost){
		
		return this.getHTTP().GET(String.format("/vhosts/%s/permissions", encodeSlashes(vhost)), PERMISSION_COLLECTION);
	}
	
	public Optional<Permission> permissionsForUser(String user){
		
		return permissionsForUser("/", user);
	}
	
	public Optional<Permission> permissionsForUser(String vhost, String user){
		
		return this.getHTTP().GET(String.format("/permissions/%s/%s", encodeSlashes(vhost), user), PERMISSION);
	}

}

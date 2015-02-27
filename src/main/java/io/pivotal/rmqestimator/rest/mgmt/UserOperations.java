package io.pivotal.rmqestimator.rest.mgmt;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Permission;
import io.pivotal.rmqestimator.rest.mgmt.model.User;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;

import com.google.common.base.Optional;

public class UserOperations extends BaseFluent {

	public UserOperations(HttpContext http, RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}
	
	public Collection<User> all(){
		
		return this.getHTTP().GET("/users", USER_COLLECTION).get();
	}
	
	public User whoAmI(){
		
		return this.getHTTP().GET("/whoami", USER).get();
	}
	
	public Optional<Collection<Permission>> permissionsFor(String user){
		
		return this.getHTTP().GET(String.format("/users/%s/permissions", user), PERMISSION_COLLECTION);
	}
	
	public Optional<User> get(String username){
		
		return this.getHTTP().GET(String.format("/users/%s", username), USER);
	}
	
	public UserOperations create(User user){
		
		this.getHTTP().PUT(String.format("/users/%s", user.getName()), user);
		
		return this;
	}
	
	public UserOperations delete(String name){
		
		this.getHTTP().DELETE(String.format("/users/%s", name));
		
		return this;
	}

}

package io.pivotal.rmqestimator.rest.mgmt;


import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Connection;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;
import java.util.Map;

public class ConnectionOperations extends BaseFluent {

	public ConnectionOperations(HttpContext http,
			RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}

	public static final String CONNECTIONS="/connections";
	public static final String FORMAT="%s";
	public static final String HEADERXRSN="X-Reason";


    public Collection<Connection> all(){

        return this.getHTTP().GET(CONNECTIONS, CONNECTION_COLLECTION).get();
    }

    public Optional<Connection> get(String name){

        return this.getHTTP().GET(String.format(CONNECTIONS+FORMAT, name), CONNECTION);
    }

    public ConnectionOperations forceDisconnect(String name){

        this.getHTTP().DELETE(String.format(CONNECTIONS+FORMAT, name));

        return this;
    }

    public ConnectionOperations forceDisconnect(String name, String reason){

        Map<String, Object> headers = Maps.newHashMap();

        headers.put(HEADERXRSN, reason);

        this.getHTTP().DELETE(String.format(CONNECTIONS+FORMAT, name), headers);

        return this;
    }

}

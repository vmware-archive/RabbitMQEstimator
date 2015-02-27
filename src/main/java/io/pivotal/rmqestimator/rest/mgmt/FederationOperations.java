package io.pivotal.rmqestimator.rest.mgmt;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.ValueWrapper;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.ExchangeFederationOptions;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationLink;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationOptions;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationPolicy;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.QueueFederationOptions;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;


public class FederationOperations extends BaseFluent {


    public FederationOperations(HttpContext http,
			RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}

	public FederationOperations establishConnectionWith(String connectionName, ExchangeFederationOptions options){

        return establish("/", connectionName, options);
    }

    public FederationOperations establishConnectionWith(String vhost, String connectionName, ExchangeFederationOptions options){

        return establish(vhost, connectionName, options);
    }

    public FederationOperations establishConnectionWith(String connectionName, QueueFederationOptions options){

        return establish("/", connectionName, options);
    }

    public FederationOperations establishConnectionWith(String vhost, String connectionName, QueueFederationOptions options){

        return establish(vhost, connectionName, options);
    }

    private FederationOperations establish(String vhost, String connectionName, FederationOptions options){

        // For some reason, they wrap the options object in a "value" parameter.
        ValueWrapper value = new ValueWrapper(options);

        this.getHTTP().PUT(String.format("/parameters/federation-upstream/%s/%s", encodeSlashes(vhost), connectionName), value);

        return this;
    }

    public FederationOperations createPolicy(String name, FederationPolicy policy){

        return createPolicy("/", name, policy);
    }

    public FederationOperations createPolicy(String vhost, String name, FederationPolicy policy){

        this.getHTTP().PUT(String.format("/policies/%s/%s", encodeSlashes(vhost), name), policy);

        return this;
    }

    public Collection<FederationLink> links(){

        return this.getHTTP().GET("/federation-links", FEDERATION_LINK_COLLECTION).get();
    }

}

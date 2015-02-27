package io.pivotal.rmqestimator.rest.mgmt;

import io.pivotal.rmqestimator.rest.HttpContext;
import io.pivotal.rmqestimator.rest.mgmt.model.Parameter;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.Collection;

import com.google.common.base.Optional;

public class ParameterOperations extends BaseFluent {
	public ParameterOperations(HttpContext http,
			RabbitNodeMgmtService mgmtService) {
		super(http, mgmtService);
	}

    public Collection<Parameter> all(){

        return this.getHTTP().GET("/parameters", PARAMETER_COLLECTION).get();
    }

    public Optional<Collection<Parameter>> all(String component){

        return this.getHTTP().GET(String.format("/parameters/%s", component), PARAMETER_COLLECTION);
    }

    public Optional<Collection<Parameter>> allOnDefault(String component){

        return allOnVHost(component, "/");
    }

    public Optional<Collection<Parameter>> allOnVHost(String component, String vhost){

        return this.getHTTP().GET(String.format("/parameters/%s/%s", component, encodeSlashes(vhost)), PARAMETER_COLLECTION);
    }

    public Optional<Parameter> get(String component, String vhost, String parameterName){

        return this.getHTTP().GET(
                String.format("/parameters/%s/%s/%s", component, encodeSlashes(vhost), parameterName), PARAMETER);
    }

    public ParameterOperations delete(String component, String vhost, String parameterName){

        this.getHTTP().DELETE(String.format("/parameters/%s/%s/%s", component, encodeSlashes(vhost), parameterName));

        return this;
    }

    public ParameterOperations create(Parameter parameter){

        this.getHTTP().PUT(String.format("/parameters/%s/%s/%s",
                parameter.getComponent(),
                encodeSlashes(parameter.getVhost()),
                parameter.getName()),
                parameter);

        return this;
    }
}

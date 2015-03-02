package io.pivotal.rmqestimator.service;

import io.pivotal.rmqestimator.model.persistence.Cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EstimatorService {

	@Autowired
	private RabbitClusterService rabbitClusterService;
	

	
	public Cluster collectClusterData(String name) {
		
		Cluster cluster=rabbitClusterService.getClusterByName(name);
        return null;
	}
}

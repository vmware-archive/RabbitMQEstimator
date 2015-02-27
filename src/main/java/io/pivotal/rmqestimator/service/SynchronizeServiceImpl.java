package io.pivotal.rmqestimator.service;

import org.springframework.stereotype.Component;

@Component
public class SynchronizeServiceImpl implements SynchronizeService{

	@Override
	public Response SynchronizeProject(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizeCluster(Long projectId, String clusterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizeVHost(Long projectId, String vHostName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizeNode(Long projectId, String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizeQueue(Long projectId, String queueName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizeExchange(Long projectId, String exchangeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response SynchronizePolicy(Long projectId, String exchangeName) {
		// TODO Auto-generated method stub
		return null;
	}

}

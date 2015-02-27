package io.pivotal.rmqestimator.service;

public interface SynchronizeService {

	public Response SynchronizeProject(Long projectId);
	public Response SynchronizeCluster(Long projectId, String clusterName);
	public Response SynchronizeVHost(Long projectId, String vHostName);
	public Response SynchronizeNode(Long projectId, String nodeName);
	public Response SynchronizeQueue(Long projectId, String queueName);
	public Response SynchronizeExchange(Long projectId, String exchangeName);
	public Response SynchronizePolicy(Long projectId, String exchangeName);
}

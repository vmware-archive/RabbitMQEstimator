package io.pivotal.rmqestimator.service;

public interface ProvisionService {

	public Response provisionQueue(Long projectId,String queueName);
	public Response provisionNode(Long projectId, String nodeName);
	public Response provisionProject(Long projectId);
	public Response provisionPolicy(Long projectId, String policyName);
	public Response provisionVHost(Long projectId, String vHostName);
	public Response provisionExchange(Long projectId, String exchangeName);
}

package io.pivotal.rmqestimator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
/**
 * Provision a specific cluster, node, etc through the rest interface
 * @return
 */
	@RequestMapping("/provision/")
	public String provision() {
		return "Greetings from RabbitMQEstimationApp";
	}
	
	@RequestMapping("/setServer")
	public String setServer() {
		return "ServerInfo";
	}
}

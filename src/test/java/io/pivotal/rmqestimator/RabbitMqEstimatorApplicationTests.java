package io.pivotal.rmqestimator;

import java.util.ArrayList;

import io.pivotal.rmqestimator.RabbitMqEstimatorApplication;
import io.pivotal.rmqestimator.model.persistence.Project;
import io.pivotal.rmqestimator.model.persistence.Cluster;
import io.pivotal.rmqestimator.model.persistence.ClusterType;
import io.pivotal.rmqestimator.model.persistence.repository.ProjectJPARepository;
import io.pivotal.rmqestimator.model.persistence.repository.ClusterTypeJPARepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RabbitMqEstimatorApplication.class)
@WebAppConfiguration
public class RabbitMqEstimatorApplicationTests {

	@Autowired
	ClusterTypeJPARepository rabbitMQClusterTypeRepo;
	
    @Autowired
    ProjectJPARepository projectRepo;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateProject() {
	  Project project=new Project();
	  project.setAnalysisType("TEST");
	  project.setCreatorName("TEST");
	  project.setDeployToCloudFoundryBosh(true);
	  project.setDeployToDocker(true);
	  project.setDeployToCloudFoundryService(true);
	  project.setId(1L);
	  project.setName("TESTPROJECT");
	  project.setRabbitMQClusters(new ArrayList<Cluster>());
	  projectRepo.save(project);
	}
	
	@Test
	public void testCreateRabbitMQCluster() {
		ClusterType clusterType=new ClusterType();
		clusterType.setName("PERSISTENT");
		ClusterType persistent=rabbitMQClusterTypeRepo.save(clusterType);
		Cluster cluster=new Cluster();
		cluster.setClusterType(persistent);
		cluster.setName("TESTCLUSTER");
		cluster.setNetTickTime(1000L);
	}
	
	@Test
	public void testCreateExchange() {
		
	}
}

package io.pivotal.rmqestimator.model.persistence.repository;

import java.util.List;

import io.pivotal.rmqestimator.model.persistence.Cluster;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="cluster",path="cluster")
public interface ClusterJPARepository extends JpaRepository<Cluster, Long> {
     @Query
	 List<Cluster> findByName(String lastname);
}

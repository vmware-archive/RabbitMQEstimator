package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.ClusterType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel="clusterType",path="clusterType")
public interface ClusterTypeJPARepository extends JpaRepository<ClusterType, Long> {

}

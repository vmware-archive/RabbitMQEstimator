package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.Node;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel="node",path="node")
public interface NodeJPARepository extends JpaRepository<Node, Long> {

}

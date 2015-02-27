package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.Queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="queue",path="queue")
public interface QueueJPARepository extends JpaRepository<Queue, Long>{

}

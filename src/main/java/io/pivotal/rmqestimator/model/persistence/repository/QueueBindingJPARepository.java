package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.QueueBinding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel="queueBinding",path="queueBinding")
public interface QueueBindingJPARepository extends JpaRepository<QueueBinding, Long> {

}

package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.Policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel="policy",path="policy")
public interface PolicyJPARepository extends JpaRepository<Policy, Long> {

}

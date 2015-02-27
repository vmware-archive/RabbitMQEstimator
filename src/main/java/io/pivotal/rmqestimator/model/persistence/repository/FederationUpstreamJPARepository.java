package io.pivotal.rmqestimator.model.persistence.repository;

import io.pivotal.rmqestimator.model.persistence.FederationUpstream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel="federationUpstream",path="federationUpstream")
public interface FederationUpstreamJPARepository extends JpaRepository<FederationUpstream, Long> {

}

package io.pivotal.rmqestimator.model.persistence.repository;

import java.util.List;

import io.pivotal.rmqestimator.model.persistence.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="project",path="project")
public interface ProjectJPARepository extends JpaRepository<Project, Long> {
	List<Project> findByName(String name);
}

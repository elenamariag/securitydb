package com.security.logics.repository;

import com.security.logics.model.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository <ProjectEntity, Long> {
}

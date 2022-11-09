package com.security.logics.repository;

import com.security.logics.model.uniform.UniformEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniformRepository extends CrudRepository <UniformEntity, Long> {
}

package com.security.logics.repository;

import com.security.logics.model.SecurityGuardUniformProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityGuardUniformProjectRepository extends CrudRepository<SecurityGuardUniformProjectEntity, Long> {

}

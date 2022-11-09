package com.security.logics.repository;

import com.security.logics.model.SecurityGuardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityGuardRepository extends CrudRepository <SecurityGuardEntity, Long> {
}

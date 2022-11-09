package com.security.logics.repository;

import com.security.logics.model.SecurityBadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityBadgeRepository extends CrudRepository <SecurityBadgeEntity, Long> {
}

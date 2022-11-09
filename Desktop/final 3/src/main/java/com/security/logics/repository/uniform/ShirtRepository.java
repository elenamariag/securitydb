package com.security.logics.repository.uniform;

import com.security.logics.model.uniform.ShirtEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShirtRepository extends CrudRepository <ShirtEntity, Long> {
  ShirtEntity findByShirtName(String shirtName);
}

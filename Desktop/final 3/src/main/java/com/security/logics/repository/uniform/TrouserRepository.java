package com.security.logics.repository.uniform;

import com.security.logics.model.uniform.TrouserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrouserRepository extends CrudRepository <TrouserEntity, Long> {
  
  TrouserEntity findByTrouserName(String trouserName);
}

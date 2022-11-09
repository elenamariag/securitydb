package com.security.logics.repository.uniform;

import com.security.logics.model.uniform.TieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TieRepository extends CrudRepository <TieEntity, Long> {
  TieEntity findByName(String tieName);
}

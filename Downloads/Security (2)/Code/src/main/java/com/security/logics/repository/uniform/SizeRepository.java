package com.security.logics.repository.uniform;

import com.security.logics.model.uniform.SizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
    SizeEntity findBySize(String sizeName);
}

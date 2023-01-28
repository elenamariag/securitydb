package com.security.logics.repository.uniform;

import com.security.logics.model.uniform.ColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<ColorEntity, Long> {
    ColorEntity findByColorName(String colorName);
}

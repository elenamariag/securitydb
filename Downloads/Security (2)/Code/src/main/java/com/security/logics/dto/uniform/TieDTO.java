package com.security.logics.dto.uniform;

import com.security.logics.model.uniform.ColorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TieDTO {

    private Long tieId;

    private String name;

    private ColorEntity color;

}

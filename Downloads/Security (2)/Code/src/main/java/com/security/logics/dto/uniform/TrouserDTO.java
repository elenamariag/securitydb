package com.security.logics.dto.uniform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrouserDTO {

    private Long trouserId;

    private String trouserName;

    private ColorDTO color;

    private SizeDTO size;
}


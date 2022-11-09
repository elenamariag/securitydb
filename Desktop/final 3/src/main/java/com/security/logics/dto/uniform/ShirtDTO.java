package com.security.logics.dto.uniform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShirtDTO
{
    private Long shirtId;
    private String shirtName;
    private ColorDTO color;
    private SizeDTO size;
}

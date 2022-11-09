package com.security.logics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityBadgeDTO
{

    private Long securityBadgeId;
    private String securityBadgeNr;
    private String name;
    private String colourSecurityBadge;
}

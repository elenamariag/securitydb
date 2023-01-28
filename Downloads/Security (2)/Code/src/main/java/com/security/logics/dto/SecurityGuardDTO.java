package com.security.logics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGuardDTO {

    private Long securityGuardId;

    private String firstName;

    private String lastName;

    private String address;

    private String dateOfBirth;

    private String certificate;

    private String email;

    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private CompanyDTO companyDTO;

    private SecurityBadgeDTO securityBadge;

    private String[] diploma;
}

package com.security.logics.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long companyId;

    private String companyName;

    private String companyAddress;

    private String email;

    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}

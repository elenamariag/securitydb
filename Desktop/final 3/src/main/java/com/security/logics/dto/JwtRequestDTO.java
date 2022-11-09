package com.security.logics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}

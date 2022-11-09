package com.security.logics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO
{

  private Long companyId;
  private String companyName;
  private String companyAddress;
  private String email;
  private String password;
}

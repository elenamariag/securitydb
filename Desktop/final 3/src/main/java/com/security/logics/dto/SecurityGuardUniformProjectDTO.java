package com.security.logics.dto;

import com.security.logics.dto.uniform.UniformDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGuardUniformProjectDTO
{
  private Long id;
  private Long requiredUniforms;
  private SecurityGuardDTO securityGuard;
  private UniformDTO uniform;
  private ProjectDTO project;
}


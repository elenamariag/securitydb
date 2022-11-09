package com.security.logics.dto.uniform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniformDTO
{
  private Long uniformId;
  private Long totalAvailableUniforms;
  private TrouserDTO trouser;
  private TieDTO tie;
  private ShirtDTO shirt;
}

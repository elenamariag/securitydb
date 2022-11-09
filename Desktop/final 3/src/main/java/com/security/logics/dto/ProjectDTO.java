package com.security.logics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO
{
    private Long projectId;
    private String projectName;
    private String projectAddress;
    private int totalSecurityGuards;
}

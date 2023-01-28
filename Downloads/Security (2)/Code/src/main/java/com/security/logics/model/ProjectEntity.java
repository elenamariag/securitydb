package com.security.logics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long projectId;

    private String projectName;

    private String projectAddress;

    private int totalSecurityGuards;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<SecurityGuardUniformProjectEntity> securityGuardUniformProjectEntities;
}

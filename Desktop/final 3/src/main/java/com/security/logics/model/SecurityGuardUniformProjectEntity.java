package com.security.logics.model;

import com.security.logics.model.uniform.UniformEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SecurityGuardUniformProject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGuardUniformProjectEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requiredUniforms;
    
    @ManyToOne
    @JoinColumn (name="security_guard_id")
    private SecurityGuardEntity securityGuard;

    @ManyToOne
    @JoinColumn (name="uniform_id")
    private UniformEntity uniform;

    @ManyToOne
    @JoinColumn (name="project_id")
    private ProjectEntity project;


}


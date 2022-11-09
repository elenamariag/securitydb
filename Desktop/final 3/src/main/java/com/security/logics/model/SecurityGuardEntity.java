package com.security.logics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table (name = "SecurityGuard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGuardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long securityGuardId;
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth;
    private String certificate;
    @Column (unique = true)
    private String email;
    private String password;
    
    @Lob
    private byte[] diploma; 


    @ManyToOne ()
    @JoinColumn (name = "company_id")
    private CompanyEntity companyEntity;

    @OneToOne
    @JoinColumn (name="security_badge_id")
    private SecurityBadgeEntity securityBadge;

    @OneToMany (mappedBy = "securityGuard", fetch = FetchType.LAZY)
    private List<SecurityGuardUniformProjectEntity> securityGuardUniformProjectEntities;

}

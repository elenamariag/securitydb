package com.security.logics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SecurityGuard")
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

    @Column(unique = true)
    private String email;

    private String password;

    @Lob
    private byte[] diploma;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @OneToOne
    @JoinColumn(name = "security_badge_id")
    private SecurityBadgeEntity securityBadge;

    @OneToMany(mappedBy = "securityGuard", fetch = FetchType.LAZY)
    private List<SecurityGuardUniformProjectEntity> securityGuardUniformProjectEntities;

}

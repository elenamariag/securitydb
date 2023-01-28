package com.security.logics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SecurityBadge")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityBadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long securityBadgeId;

    private String securityBadgeNr;

    private String name;

    private String colourSecurityBadge;

    @OneToOne(mappedBy = "securityBadge")
    private SecurityGuardEntity securityGuardEntity;
}

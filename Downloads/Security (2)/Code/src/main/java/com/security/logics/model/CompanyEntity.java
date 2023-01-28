package com.security.logics.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long companyId;

    private String companyName;

    private String companyAddress;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "company")
    List<UserEntity> users;

    @OneToMany(mappedBy = "companyEntity")
    List<SecurityGuardEntity> securityGuardEntityList;


}

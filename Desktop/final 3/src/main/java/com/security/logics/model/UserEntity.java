package com.security.logics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Entity
@Table (name ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String email;
    private String address;
    private String dateOfBirth;
    private String userType;
    @Lob
    private Blob diploma;

    @ManyToOne
    @JoinColumn (name="company_id")
    private CompanyEntity company;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    }
    )
    private Set<Role> role;

}

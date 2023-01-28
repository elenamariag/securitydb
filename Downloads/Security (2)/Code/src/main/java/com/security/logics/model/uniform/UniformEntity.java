package com.security.logics.model.uniform;

import com.security.logics.model.SecurityGuardUniformProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Uniform")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniformId;

    private long totalAvailableUniforms;

    @ManyToOne
    @JoinColumn(name = "trouser_id")
    private TrouserEntity trouser;

    @ManyToOne
    @JoinColumn(name = "tie_id")
    private TieEntity tie;

    @ManyToOne
    @JoinColumn(name = "shirt_id")
    private ShirtEntity shirt;

    @OneToMany(mappedBy = "uniform", fetch = FetchType.LAZY)
    private List<SecurityGuardUniformProjectEntity> securityGuardUniformProjectEntities;
}

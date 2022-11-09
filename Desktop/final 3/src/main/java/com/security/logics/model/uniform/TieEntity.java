package com.security.logics.model.uniform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Tie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tieId;
    private String name;

    @ManyToOne
    @JoinColumn (name ="color_id")
    private ColorEntity color;

    @OneToMany (mappedBy = "tie", fetch = FetchType.LAZY)
    private List <UniformEntity> uniforms;
}

package com.security.logics.model.uniform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Trouser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrouserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trouserId;
    private String trouserName;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @OneToMany(mappedBy = "trouser", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UniformEntity> uniforms;


}


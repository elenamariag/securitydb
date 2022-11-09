package com.security.logics.model.uniform;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Shirt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShirtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="shirt_id")
    private Long shirtId;
    private String shirtName;


    @ManyToOne
    @JoinColumn (name="color_id")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn (name="size_id")
    private SizeEntity size;

    @OneToMany (mappedBy = "shirt", fetch = FetchType.LAZY)
    private List <UniformEntity> uniforms;

}

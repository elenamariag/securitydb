package com.security.logics.model.uniform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Size")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;
    private String size;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShirtEntity> shirts;

    @OneToMany (mappedBy = "size")
    @JsonIgnore
    private List<TrouserEntity> trousers;
}

package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ciudad")
public class Ciudad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ciuId")
    private Long ciuId;

    @Column(name = "ciuNombre")
    private String ciuNombre;

    @ManyToOne
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private Provincia proId;

    @JsonIgnore
    @OneToMany(mappedBy = "ciuId")
    private List<Persona> listaPersonas;
}

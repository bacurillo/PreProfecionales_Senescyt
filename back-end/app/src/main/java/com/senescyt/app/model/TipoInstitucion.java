package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TipoInstitucion")
public class TipoInstitucion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipId")
    private Long tipId;

    @Column(name = "tipNombre")
    private String tipNombre;

    @Column(name = "tipEstado")
    private int tipEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "tipId")
    private List<Institucion> listaInstituciones;
}

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
@Table(name = "Puesto")
public class Puesto /*cargo*/ implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pueId")
    private Long pueId;

    @Column(name = "pueNombre")
    private String pueNombre;

    @JsonIgnore
    @OneToMany(mappedBy = "pueId")
    private List<CargoUsuario> listaCargoUsuarios;
}

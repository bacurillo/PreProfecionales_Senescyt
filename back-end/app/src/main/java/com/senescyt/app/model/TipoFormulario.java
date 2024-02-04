package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TipoFormulario")
public class TipoFormulario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tiFoId")
    private Long tiFoId;

    @Column(name = "tiFoNombre")
    private String tiFoNombre;
    @Column(name = "tiFoEstado")
    private int tiFoEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "tiFoId")
    private List<Permisos> listaPermisos;

}

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
@Table(name = "Funciones")
public class Funciones implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funId")
    private Long funId;

    @Column(name = "funNombre")
    private String funNombre;


    @Column(name = "funEstado")
    private int funEstado;
    @JsonIgnore
    @OneToMany(mappedBy = "funId")
    private List<Usuario> listaUsuarios;

}

package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Zonales")
public class Zonales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zonId")
    private Long zonId;

    @Column(name = "zonNombre")
    private String zonNombre;

    @Column(name = "zonCodigo")
    private String zonCodigo;

    @Column(name = "zonEstado")
    private int zonEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "zonId")
    private List<Usuario> listaUsuarios;
}

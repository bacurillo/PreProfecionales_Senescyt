package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Regimen")
public class Regimen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regId")
    private Long regId;

    @Column(name = "regNombre")
    private String regNombre;

    @Column(name = "regEstado")
    private int regEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "regId")
    private List<Usuario> listaUsuarios;
}

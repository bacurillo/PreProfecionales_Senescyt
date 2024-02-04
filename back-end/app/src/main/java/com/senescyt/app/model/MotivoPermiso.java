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
@Table(name = "MotivoPermiso")
public class MotivoPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motId")
    private Long motId;

    @Column(name = "motNombre")
    private String motNombre;

    @Column(name = "motEstado")
    private int motEstado;

    @Column(name = "motDescripcionLarga",length = 2000000000)
    private String motDescripcionLarga;

    @Column(name = "motDescripcionCorta",length = 2000000000)
    private String motDescripcionCorta;

    @JsonIgnore
    @OneToMany(mappedBy = "motId")
    private List<Permisos> listaPermisos;

}

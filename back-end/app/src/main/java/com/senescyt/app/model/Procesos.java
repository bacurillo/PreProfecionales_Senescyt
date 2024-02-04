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
@Table(name = "Procesos")
public class Procesos /*zona*/ implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procId")
    private Long procId;

    @Column(name = "procNombre")
    private String procNombre;

    @Column(name = "procEstado")
    private int procEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "procId")
    private List<Subprocesos> listaSubprocesos;

//    @JsonIgnore
//    @OneToMany(mappedBy = "procId")
//    private List<Usuario> listaUsuarios;
}

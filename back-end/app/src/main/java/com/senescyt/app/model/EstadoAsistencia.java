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
@Table(name = "EstadoAsistencia")
public class EstadoAsistencia implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estId")
    private Long estId;

    @Column(name = "estNombre")
    private String estNombre;
    @Column(name = "estEstado")
    private boolean estEstado;

//    @JsonIgnore
//    @OneToMany(mappedBy = "estadoAsistencia", cascade = CascadeType.ALL)
//    private List<Asistencia> listaAsistencia;

}

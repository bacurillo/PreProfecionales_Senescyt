package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asistencia")
public class Asistencia implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asisId")
    private Long asisId;

    private String asisNombre;

    private String asisDpto;

    private Long asisNoLector;

    private String asisFechaHora;

    private String asisEstado;

    private Long asisLocacionId;

    private Long asisIdNumero;

    private Long asisCodTrabajo;

    private String asisVerificaCod;

    private Long asisNoTarjeta;

    private String asisEstadoStr;// Puntual o Retraso

    private String asisNombreArchivo;

    private Timestamp asisFechaArchivo;



    @ManyToOne
    @JoinColumn(name = "usuId", referencedColumnName = "usuId")
    private Usuario usuId;

//    @ManyToOne
//    @JoinColumn(name = "estId", referencedColumnName = "estId")
//    private EstadoAsistencia estadoAsistencia;

}

package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Permisos")
public class Permisos implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permId")
    private Long permId;

    @Column(name = "permObservacion", length = 1000)
    private String permObservacion;
    @Column(name = "permFechaEmision")
    private Date permFechaEmision;
    @Column(name = "permFechaInicio")
    private String permFechaInicio;
    @Column(name = "permFechaFin")
    private String permFechaFin;

    /*estados del permiso
     * 1: Aprobado por Jefe General
     * 2: Aprobado por Jefe De Unidad
     * 3: En Espera
     * 4: Rechazado
     * */
    @Column(name = "permEstado")
    private int permEstado;
    @Column(name = "permHorasInicio")
    private String permHorasInicio;
    @Column(name = "permHorasFin")
    private String permHorasFin;
    @Column(name = "permDocumento", columnDefinition = "LONGTEXT")
    private String permDocumento;


    @ManyToOne
    @JoinColumn(name = "usuId", referencedColumnName = "usuId")
    private Usuario usuId;

    @ManyToOne
    @JoinColumn(name = "tiFoId", referencedColumnName = "tiFoId")
    private TipoFormulario tiFoId;

    @ManyToOne
    @JoinColumn(name = "motId", referencedColumnName = "motId")
    private MotivoPermiso motId;

    @ManyToOne
    @JoinColumn(name = "tiPeId", referencedColumnName = "tiPeId")
    private TipoPermiso tiPeId;

}

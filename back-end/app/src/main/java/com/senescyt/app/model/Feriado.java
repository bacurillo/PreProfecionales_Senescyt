package com.senescyt.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Feriado")
public class Feriado {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ferId")
    private Long ferId;

    @Column(name = "ferFechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ferFechaInicio;

    @Column(name = "ferFechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ferFechaFin;

    @Column(name = "ferEstado")
    private int ferEstado;

}

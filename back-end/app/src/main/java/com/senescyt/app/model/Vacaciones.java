package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vacaciones")
public class Vacaciones implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacId")
    private Long vacId;

    /* ESTOS VAN EN EL ENCABEZADO */
    @Column(name = "vacDiasAnticipacion")
    private double vacDiasAnticipacion;
    @Column(name = "vacDiasCaducados")
    private int vacDiasCaducados;
    @Column(name = "vacDiasUsados")
    private double vacDiasUsados;
    @Column(name = "vacTotalDiasDisponibles")
    private double vacTotalDiasDisponibles;

    /* ESTOS VAN EN LA TABLA */
    @Column(name = "vacDetalle")
    private String vacDetalle;
    @Column(name = "vacDias")
    private int vacDias;
    @Column(name = "vacHoras")
    private int vacHoras;
    @Column(name = "vacMinutos")
    private int vacMinutos;
    @Column(name = "vacTotalenDias")
    private double vacTotalenDias;
    @Column(name = "vacNoGozadas")
    private double vacNoGozadas;
    @Column(name = "vacSaldo")
    private double vacSaldo;
    @Column(name = "vacFecha")
    private Date vacFecha;
    @Column(name = "vacFechaHoy")
    private Date vacFechaHoy;
    @Column(name = "vacEstado")
    private int vacEstado;
    @Column(name = "vacDiasGanados")
    private double vacDiasGanados;

    @ManyToOne
    @JoinColumn(name = "usuId", referencedColumnName = "usuId")
    private Usuario usuId;

    @JsonIgnore
    @OneToMany(mappedBy = "vacId")
    private List<Periodo> listaPeriodo;


}

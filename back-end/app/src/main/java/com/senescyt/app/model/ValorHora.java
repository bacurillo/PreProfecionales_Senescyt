package com.senescyt.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ValorHora")
public class ValorHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valorId")
    private Long valorId;

    @Column(name = "valorHora")
    private double valorHora;

    @Column(name = "valorFecha")
    private Timestamp valorFecha;

    @Column(name = "valorEstado")
    private int valorEstado;
}

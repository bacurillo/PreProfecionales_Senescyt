/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ALEJO PC
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perId;

    @Column(unique = true, name = "perCedula")
    private String perCedula;

    private String perNombre;
    private String perApellido;
    private String perDireccion;
    private String perTelefono;

    @Column(name = "perFechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date perFechaNacimiento;

    @OneToOne(mappedBy = "usuPerId")
    @JsonIgnore // Esta anotación evita que se serialice el campo usuario
    private Usuario usuario;



    @ManyToOne
    @JoinColumn(name = "ciuId", referencedColumnName = "ciuId")
    private Ciudad ciuId;
}
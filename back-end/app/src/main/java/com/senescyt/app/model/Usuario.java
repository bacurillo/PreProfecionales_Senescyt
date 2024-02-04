/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senescyt.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author ALEJO PC
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuId")
    private Long usuId;

    @Column(name = "usuIdLector")
    private Long usuIdLector;

    @Column(name = "usuIdJefe")
    private Long usuIdJefe;

    @Basic
    private String usuNombreUsuario;

    @Basic
    private String usuContrasena;

    @Column(name = "usuCorreo")
    private String usuCorreo;

    @Column(name = "foto", columnDefinition = "LONGTEXT")
    private String foto;

    @Column(name = "titulo", columnDefinition = "LONGTEXT")
    private String titulo;

    @Column(name = "usuEstado")
    private int usuEstado;

    @Column(name = "usuSaldoVacacional")
    private double usuSaldoVacacional;

    @Column(name = "usuFechaRegistro")
    private Date usuFechaRegistro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuPerId", referencedColumnName = "perId")
//    @JsonIgnore // Esta anotación evita que se serialice el campo usuPerId
    private Persona usuPerId;


    @ManyToOne
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Rol rolId;

    @ManyToOne
    @JoinColumn(name = "regId", referencedColumnName = "regId")
    private Regimen regId;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rolId.getRolNombre())));
    }

    @ManyToOne
    @JoinColumn(name = "funId", referencedColumnName = "funId")
    private Funciones funId;

    @ManyToOne
    @JoinColumn(name = "insId", referencedColumnName = "insId")
    private Institucion insId;

    @ManyToOne
    @JoinColumn(name = "horId", referencedColumnName = "horId")
    private Horarios horId;

    @ManyToOne
    @JoinColumn(name = "subId", referencedColumnName = "subId")
    private Subprocesos subId;

    @ManyToOne
    @JoinColumn(name = "zonId", referencedColumnName = "zonId")
    private Zonales zonId;

    @Override
    public String getPassword() {
        return this.getUsuContrasena();
    }

    @Override
    public String getUsername() {
        return this.usuNombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "usuId")
    private List<Permisos> listaPermisos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuId")
    private List<Vacaciones> listaVacaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "usuId", cascade = CascadeType.ALL)
    private List<Asistencia> listaAsistencia;

    @Override
    public boolean isEnabled() {
        return true;
    }
}

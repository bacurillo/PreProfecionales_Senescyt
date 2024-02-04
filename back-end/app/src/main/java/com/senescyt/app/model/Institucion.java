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
@Table(name = "Institucion")
public class Institucion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insId")
    private Long instId;

    @Column(name = "insNombre")
    private String instNombre;
    @Column(name = "intDireccion")
    private String instDireccion;
    @Column(name = "instEstado")
    private int instEstado;
    @Column(name = "instCodigo")
    private String instCodigo;
    @Column(name = "instReferencia")
    private String instReferencia;

    @ManyToOne
    @JoinColumn(name = "tipId", referencedColumnName = "tipId")
    private TipoInstitucion tipId;

    @JsonIgnore
    @OneToMany(mappedBy = "insId")
    private List<Usuario> listaUsuarios;

}

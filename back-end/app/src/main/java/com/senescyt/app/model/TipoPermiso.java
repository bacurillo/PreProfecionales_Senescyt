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
@Table(name = "TipoPermiso")
public class TipoPermiso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tiPeId")
    private Long tiPeId;

    @Column(name = "tiPeNombre")
    private String tiPeNombre;
    @Column(name = "tiPeEstado")
    private int tiPeEstado;
    @Column(name = "tiPeDescripcion",length = 2000000000)
    private String tiPeDescripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "tiPeId")
    private List<Permisos> listaPermisos;

}

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
@Table(name = "Horarios")
public class Horarios implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horId")
    private Long horId;

    @Column(name = "horNumHoras")
    private int horNumHoras;
    @Column(name = "horHoraIngresoDia")
    private String horHoraIngresoDia;
    @Column(name = "horHoraSalidaDia")
    private String horHoraSalidaDia;
    @Column(name = "horHorasParaAlmuerzo")
    private int horHorasParaAlmuerzo;
    @Column(name = "horHoraIngresoTarde")
    private String horHoraIngresoTarde;
    @Column(name = "horHoraSalidaTarde")
    private String horHoraSalidaTarde;

    @Column(name = "horEstado")
    private int horEstado;

    @JsonIgnore
    @OneToMany(mappedBy = "horId")
    private List<Usuario> listaUsuarios;

}

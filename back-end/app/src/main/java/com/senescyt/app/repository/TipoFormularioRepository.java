package com.senescyt.app.repository;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoFormulario;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoFormularioRepository extends GenericRepository<TipoFormulario, Long> {

    @Query(value = "SELECT * FROM tipo_formulario WHERE ti_fo_estado = :est", nativeQuery = true)
    public List<TipoFormulario> getTipoFormularioByEstado(@Param("est") int est);

}

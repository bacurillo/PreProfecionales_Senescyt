package com.senescyt.app.repository;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotivoPermisoRepository extends GenericRepository<MotivoPermiso,Long> {

    @Query(value = "SELECT * FROM motivo_permiso where mot_estado = :est", nativeQuery = true)
    public List<MotivoPermiso> getMotivosByEstado(@Param("est") int est);
}

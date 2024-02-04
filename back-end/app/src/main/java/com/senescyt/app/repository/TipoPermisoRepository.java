package com.senescyt.app.repository;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoPermiso;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPermisoRepository extends GenericRepository<TipoPermiso, Long> {

    @Query(value = "SELECT * FROM tipo_permiso WHERE ti_pe_estado = :est", nativeQuery = true)
    public List<TipoPermiso> getTipoPermisoByEstado(@Param("est") int est);

    @Query(value = "SELECT * FROM tipo_permiso WHERE ti_pe_estado = :est AND (LOWER(ti_pe_nombre) LIKE CONCAT('%', :search, '%') OR UPPER(ti_pe_nombre) LIKE CONCAT('%', :search, '%'))", nativeQuery = true)
    public List<TipoPermiso> searchTipopermiso(@Param("search") String search, @Param("est") int est);

    @Query(value = "SELECT * FROM tipo_permiso WHERE ti_pe_id = :id", nativeQuery = true)
    TipoPermiso getTipoPermsioById(@Param("id") Long id);
}

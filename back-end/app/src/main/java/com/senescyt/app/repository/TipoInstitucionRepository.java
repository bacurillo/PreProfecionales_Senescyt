package com.senescyt.app.repository;

import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoInstitucionRepository extends GenericRepository<TipoInstitucion, Long> {

    @Query(value = "SELECT * FROM tipo_institucion where tip_estado = :est", nativeQuery = true)
    public List<TipoInstitucion> getTipoInstitucionByEstado(@Param("est") int est);

    @Query(value = "SELECT * \n" +
            "FROM tipo_institucion \n" +
            "WHERE tip_estado = :est \n" +
            "  AND (LOWER(tip_nombre) LIKE CONCAT('%', :search, '%') OR UPPER(tip_nombre) LIKE CONCAT('%', :search, '%'))", nativeQuery = true)
    List<TipoInstitucion> searchTipoInstitucion(@Param("search") String search, @Param("est") int est);

}

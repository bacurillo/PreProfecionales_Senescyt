package com.senescyt.app.repository;

import com.senescyt.app.model.Institucion;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsitucionRepository extends GenericRepository<Institucion, Long> {

    @Query(value = "SELECT * FROM institucion where tip_id = :tipId", nativeQuery = true)
    List<Institucion> getInstitucionByTipId(@Param("tipId") Long tipId);

    @Query(value = "SELECT i.* FROM institucion i JOIN tipo_institucion t ON i.tip_id = t.tip_id WHERE t.tip_estado = :tipid AND i.inst_estado = :instid ", nativeQuery = true)
    public List<Institucion> getInstitucionesByTipId(@Param("tipid") int tipid, @Param("instid") int instid);

    @Query(value = "SELECT * FROM institucion WHERE inst_estado = :est", nativeQuery = true)
    List<Institucion> getInstitucionesByEstado(@Param("est") int est);

    @Query(value = "SELECT * FROM institucion where ins_id = :id", nativeQuery = true)
    Institucion getInstitucionById(@Param("id") Long id);

    @Query(value = "SELECT * FROM bd_snc.institucion \n" +
            "WHERE inst_estado = :est \n" +
            "AND (\n" +
            "    LOWER(inst_codigo) LIKE CONCAT('%', :search, '%') OR UPPER(inst_codigo) LIKE CONCAT('%', :search, '%')\n" +
            "    OR LOWER(int_direccion) LIKE CONCAT('%', :search, '%') OR UPPER(int_direccion) LIKE CONCAT('%', :search, '%')\n" +
            "    OR LOWER(ins_nombre) LIKE CONCAT('%', :search, '%') OR UPPER(ins_nombre) LIKE CONCAT('%', :search, '%')\n" +
            "    OR LOWER(inst_referencia) LIKE CONCAT('%', :search, '%') OR UPPER(inst_referencia) LIKE CONCAT('%', :search, '%')\n" +
            ")", nativeQuery = true)
    List<Institucion> searchInstitucion(@Param("search") String search, @Param("est") int est);
}

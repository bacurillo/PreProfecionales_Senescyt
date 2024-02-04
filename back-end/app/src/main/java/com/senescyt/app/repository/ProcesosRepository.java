package com.senescyt.app.repository;

import com.senescyt.app.model.Procesos;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcesosRepository extends GenericRepository<Procesos, Long> {

    @Query(value = "SELECT * FROM procesos where proc_estado = :est", nativeQuery = true)
    List<Procesos> getProcesosByEstado(@Param("est") int est);

    @Query(value = "SELECT * FROM procesos WHERE proc_estado = :est AND (LOWER(proc_nombre) LIKE CONCAT('%', :search, '%') OR UPPER(proc_nombre) LIKE CONCAT('%', :search, '%'))", nativeQuery = true)
    List<Procesos> searchProcesos(@Param("search") String search, @Param("est") int est);
}

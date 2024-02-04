package com.senescyt.app.repository;

import com.senescyt.app.model.Periodo;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoRepository extends GenericRepository<Periodo, Long> {

    @Query(value = "SELECT * FROM periodo where peri_estado = :est", nativeQuery = true)
    public List<Periodo> getPeriodoByEstado(@Param("est") int est);

    @Query(value = "SELECT * FROM periodo WHERE (peri_actual = CAST(:search AS DATE) OR peri_anterior = CAST(:search AS DATE) OR dias_anticipacion = :search) AND peri_estado = :est", nativeQuery = true)
    List<Periodo> searchPeriodos(@Param("search") String search, @Param("est") int est);
}

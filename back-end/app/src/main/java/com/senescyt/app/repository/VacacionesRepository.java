package com.senescyt.app.repository;

import com.senescyt.app.model.Vacaciones;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacacionesRepository extends GenericRepository<Vacaciones, Long> {
    @Query(value = "SELECT * FROM vacaciones WHERE usu_id = :id", nativeQuery = true)
    List<Vacaciones> getVacacionesByUsuId(@Param("id") Long id);

    @Query(value = "SELECT vac_saldo FROM vacaciones WHERE usu_id = :userId", nativeQuery = true)
    Double getSaldoUltimoRegistroPorUsuario(@Param("userId") Long userId);
}

package com.senescyt.app.repository;

import com.senescyt.app.model.Horarios;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorariosRepository extends GenericRepository<Horarios,Long> {

    @Query(value = "SELECT * FROM horarios where hor_estado = :est", nativeQuery = true)
    public List<Horarios> getProcesosByHorario(@Param("est") int est);

    @Query(value = "SELECT * FROM horarios where hor_id = :id", nativeQuery = true)
    Horarios getHorarioById(@Param("id") Long id);

}

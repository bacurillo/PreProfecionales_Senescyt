package com.senescyt.app.repository;

import com.senescyt.app.model.Funciones;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionesRepository extends GenericRepository<Funciones, Long> {

    @Query(value = "SELECT * FROM funciones where fun_estado = :est", nativeQuery = true)
    List<Funciones> getFuncionesByEstado(@Param("est") int est);

    @Query(value = "SELECT * FROM funciones \n" +
            "WHERE (LOWER(fun_nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR UPPER(fun_nombre) LIKE UPPER(CONCAT('%', :search, '%')))\n" +
            "AND fun_estado = :est", nativeQuery = true)
    List<Funciones> searchFunciones(@Param("search") String search, @Param("est") int est);

}

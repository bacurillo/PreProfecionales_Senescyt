package com.senescyt.app.repository;

import com.senescyt.app.model.Zonales;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZonalesRepository extends GenericRepository<Zonales, Long> {

    @Query(value = "SELECT * FROM zonales WHERE zon_estado = :est", nativeQuery = true)
    List<Zonales> getZonalesByEstado(@Param("est") int est);

    @Query(value = "SELECT * \n" +
            "FROM zonales \n" +
            "WHERE zon_estado = :est \n" +
            "  AND (\n" +
            "    LOWER(zon_nombre) LIKE CONCAT('%', :search, '%') \n" +
            "    OR UPPER(zon_nombre) LIKE CONCAT('%', :search, '%')\n" +
            "    OR LOWER(zon_codigo) LIKE CONCAT('%', :search, '%') \n" +
            "    OR UPPER(zon_codigo) LIKE CONCAT('%', :search, '%')\n" +
            "  );", nativeQuery = true)
    List<Zonales> searchZonales(@Param("search") String search, @Param("est") int est);

    @Query(value = "SELECT * FROM zonales WHERE zon_id = :id", nativeQuery = true)
    Zonales getZonalById(@Param("id") Long id);
}

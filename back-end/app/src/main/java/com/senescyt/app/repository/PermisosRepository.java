package com.senescyt.app.repository;

import com.senescyt.app.model.Permisos;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisosRepository extends GenericRepository<Permisos, Long> {

    @Query(value = "SELECT * FROM permisos WHERE usu_id = :id", nativeQuery = true)
    List<Permisos> getPermisosByUsuId(@Param("id") int id);

    @Query(value = "SELECT p.* FROM permisos p JOIN usuario u ON p.usu_id = u.usu_id WHERE u.usu_id_jefe = :id", nativeQuery = true)
    List<Permisos> getPermisosByIdJefe(@Param("id") int id);

    @Query(value = "SELECT * FROM permisos WHERE perm_estado = :est", nativeQuery = true)
    List<Permisos> getPermisosForAdmin(@Param("est") int est);

    @Query(value = "SELECT p.*\n" +
            "FROM bd_snc.permisos p\n" +
            "JOIN bd_snc.usuario u ON p.usu_id = u.usu_id\n" +
            "JOIN bd_snc.persona pe ON u.usu_per_id = pe.per_id\n" +
            "WHERE (LOWER(pe.per_cedula) LIKE LOWER(CONCAT('%', :search ,'%')) OR UPPER(pe.per_cedula) LIKE UPPER(CONCAT('%', :search, '%')))" +
            "AND (p.perm_estado = 2 OR p.perm_estado = 3)", nativeQuery = true)
    List<Permisos> searchPermisos(@Param("search") String search);

    @Query(value = "SELECT * FROM permisos WHERE perm_id = :id", nativeQuery = true)
    Permisos getPermisoById(@Param("id") Long id);
}

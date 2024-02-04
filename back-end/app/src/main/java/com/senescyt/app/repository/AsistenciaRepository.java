package com.senescyt.app.repository;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.Usuario;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AsistenciaRepository extends GenericRepository<Asistencia, Long> {

    Asistencia findByAsisId(Long id);

    @Query(nativeQuery = true, value = "SELECT   " +
            "    ROW_NUMBER() OVER (ORDER BY asis_fecha_archivo , asis_nombre_archivo, usu_id ) as indice,  " +
            "    asis_nombre_archivo,  " +
            "    asis_fecha_archivo,      " +
            "    COUNT(*) as cantidad_registros,  " +
            "    usu_id  " +
            "FROM bd_snc.asistencia  " +
            "GROUP BY asis_fecha_archivo , asis_nombre_archivo, usu_id"
    )
    List<Object[]> historialArchivos();


    @Query(nativeQuery = true, value = "SELECT  " +
            "    ROW_NUMBER() OVER (ORDER BY asis_fecha_archivo, asis_nombre_archivo, usu_id) as indice,  " +
            "    asis_nombre_archivo,  " +
            "    asis_fecha_archivo,  " +
            "    COUNT(*) as cantidad_registros,  " +
            "    usu_id  " +
            "FROM bd_snc.asistencia  " +
            "WHERE LOWER(asis_nombre_archivo) LIKE LOWER(CONCAT('%', :nombre ,'%'))   " +
            "    AND (asis_fecha_archivo >= :fechaMin AND asis_fecha_archivo <= :fechaMax )  " +
            "GROUP BY asis_fecha_archivo, asis_nombre_archivo, usu_id"
    )
    List<Object[]> historialArchivosSearch(@Param("fechaMin") String fechaMin,@Param("fechaMax") String fechaMax, @Param("nombre") String nombre);


    @Query(nativeQuery = true, value = "SELECT   " +
            "    a.asis_id,   " +
            "    u.usu_id,   " +
            "    p.per_id  " +
            "FROM bd_snc.asistencia a  " +
            "JOIN bd_snc.usuario u ON (a.asis_no_lector = u.usu_id_lector)  " +
            "JOIN bd_snc.persona p ON (p.per_id = u.usu_per_id)  " +
            "WHERE (STR_TO_DATE(a.asis_fecha_hora, '%d/%m/%Y') BETWEEN :fechaMin AND :fechaMax)  " +
            "AND (p.per_cedula LIKE CONCAT ('%', :search, '%') " +
            "  OR CONCAT(LOWER(p.per_apellido), ' ', LOWER(p.per_nombre)) LIKE LOWER (CONCAT('%', :search ,'%'))" +
            "  OR CONCAT(LOWER(p.per_nombre), ' ', LOWER(p.per_apellido)) LIKE LOWER (CONCAT('%', :search ,'%'))" +
            "  OR LOWER(u.usu_nombre_usuario) LIKE LOWER (CONCAT('%', :search ,'%'))" +
            ") ORDER BY a.asis_fecha_hora, p.per_apellido, p.per_nombre"
    )
    List<Object[]> asistenciaSearch(@Param("fechaMin") String fechaMin,@Param("fechaMax") String fechaMax, @Param("search") String search);



    @Query(nativeQuery = true, value = "SELECT   " +
            "    a.asis_id,   " +
            "    u.usu_id,   " +
            "    p.per_id  " +
            "FROM bd_snc.asistencia a  " +
            "JOIN bd_snc.usuario u ON (a.asis_no_lector = u.usu_id_lector)  " +
            "JOIN bd_snc.persona p ON (p.per_id = u.usu_per_id)  " +
            "WHERE (STR_TO_DATE(a.asis_fecha_hora, '%d/%m/%Y %T') BETWEEN :fechaMin AND :fechaMax)  " +
            "AND (u.usu_id= :usuId" +
            ") ORDER BY a.asis_fecha_hora, p.per_apellido, p.per_nombre"
    )
    List<Object[]> miAsistencia(@Param("usuId") Long usuId, @Param("fechaMin") String fechaMin,@Param("fechaMax") String fechaMax);
}

package com.senescyt.app.repository;

import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.Usuario;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubprocesosRepository extends GenericRepository<Subprocesos, Long> {

    @Query(value = "SELECT * FROM subprocesos where proc_id = :idSubproceso", nativeQuery = true)
    List<Subprocesos> getSubprocesosByProcId(@Param("idSubproceso") Long idSubproceso);

    @Query(value = "SELECT s.* FROM subprocesos s JOIN procesos p ON s.proc_id = p.proc_id WHERE p.proc_estado = :estproc AND s.sub_estado = :estsub", nativeQuery = true)
    public List<Subprocesos> getSubprocesosByProcEstado(@Param("estproc") int estproc, @Param("estsub") int estsub);

    @Query(value = "SELECT * FROM bd_snc.subprocesos WHERE sub_estado = :est AND (LOWER(sub_nombre) LIKE CONCAT('%', :search, '%') OR UPPER(sub_nombre) LIKE CONCAT('%', :search, '%'))", nativeQuery = true)
    List<Subprocesos> searchSubprocesos(@Param("search") String search,@Param("est") int est);
}

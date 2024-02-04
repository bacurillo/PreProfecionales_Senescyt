/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.senescyt.app.repository;

import com.senescyt.app.model.Persona;
import com.senescyt.app.model.Rol;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALEJO PC
 */
@Repository
public interface PersonaRepository extends GenericRepository<Persona, Long> {

    @Query(value = "SELECT COUNT(*) FROM `persona` WHERE per_cedula = :ci", nativeQuery = true)
    int cedulaUnica(@Param("ci") String ci);
}

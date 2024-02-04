package com.senescyt.app.repository;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.ValorHora;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValorHoraRepository extends GenericRepository<ValorHora, Long> {

    @Query(value = "SELECT * FROM valor_hora WHERE valor_estado = :est", nativeQuery = true)
    public List<ValorHora> getValoresByEstado(@Param("est") int est);

    @Query(value = "SELECT COUNT(*) FROM valor_hora", nativeQuery = true)
    public int valorUnico();
}

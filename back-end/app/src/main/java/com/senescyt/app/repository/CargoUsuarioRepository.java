package com.senescyt.app.repository;

import com.senescyt.app.model.CargoUsuario;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoUsuarioRepository extends GenericRepository<CargoUsuario,Long> {
}

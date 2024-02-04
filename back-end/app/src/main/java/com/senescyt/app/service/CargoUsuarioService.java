package com.senescyt.app.service;

import com.senescyt.app.model.CargoUsuario;
import com.senescyt.app.repository.CargoUsuarioRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoUsuarioService extends GenericServiceImpl<CargoUsuario, Long> implements GenericService<CargoUsuario, Long> {

    @Autowired
    CargoUsuarioRepository cargoUsuarioRepository;

    @Override
    public CrudRepository<CargoUsuario, Long> getDao() {
        return cargoUsuarioRepository;
    }
}

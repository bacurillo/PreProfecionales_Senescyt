package com.senescyt.app.service;

import com.senescyt.app.model.Funciones;
import com.senescyt.app.model.Persona;
import com.senescyt.app.repository.FuncionesRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionesService extends GenericServiceImpl<Funciones, Long> implements GenericService<Funciones, Long> {

    @Autowired
    FuncionesRepository funcionesRepository;

    @Override
    public CrudRepository<Funciones, Long> getDao() {
        return funcionesRepository;
    }

    public List<Funciones> getFuncionesByEstado(int est) {
        return funcionesRepository.getFuncionesByEstado(est);
    }

    public List<Funciones> searchFunciones(String search, int est) {
        return funcionesRepository.searchFunciones(search, est);
    }
}

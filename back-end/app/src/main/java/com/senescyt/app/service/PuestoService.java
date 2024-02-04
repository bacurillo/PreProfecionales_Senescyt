package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.Puesto;
import com.senescyt.app.repository.PuestoRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PuestoService extends GenericServiceImpl<Puesto, Long> implements GenericService<Puesto, Long> {
    @Autowired
    PuestoRepository puestoRepository;
    @Override
    public CrudRepository<Puesto, Long> getDao() {
        return puestoRepository;
    }
}

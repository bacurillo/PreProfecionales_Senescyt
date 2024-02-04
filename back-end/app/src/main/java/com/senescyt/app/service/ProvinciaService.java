package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.Provincia;
import com.senescyt.app.repository.ProvinciaRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService extends GenericServiceImpl<Provincia, Long> implements GenericService<Provincia, Long> {

    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public CrudRepository<Provincia, Long> getDao() {
        return provinciaRepository;
    }
}

package com.senescyt.app.service;

import com.senescyt.app.model.EstadoAsistencia;
import com.senescyt.app.model.Persona;
import com.senescyt.app.repository.EstadoAsistenciaRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadoAsistenciaService extends GenericServiceImpl<EstadoAsistencia, Long> implements GenericService<EstadoAsistencia, Long> {

    @Autowired
    EstadoAsistenciaRepository estadoAsistenciaRepository;

    @Override
    public CrudRepository<EstadoAsistencia, Long> getDao() {
        return estadoAsistenciaRepository;
    }
}

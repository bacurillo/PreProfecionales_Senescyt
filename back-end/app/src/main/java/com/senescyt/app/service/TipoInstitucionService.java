package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.repository.TipoInstitucionRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoInstitucionService extends GenericServiceImpl<TipoInstitucion, Long> implements GenericService<TipoInstitucion, Long> {
    @Autowired
    TipoInstitucionRepository tipoInstitucionRepository;

    @Override
    public CrudRepository<TipoInstitucion, Long> getDao() {
        return tipoInstitucionRepository;
    }

    public List<TipoInstitucion> getTipoInstitucionByEstado(int est) {
        return tipoInstitucionRepository.getTipoInstitucionByEstado(est);
    }

    public List<TipoInstitucion> searchTipoInstitucion(String search,int est) {
        return tipoInstitucionRepository.searchTipoInstitucion(search,est);
    }

}


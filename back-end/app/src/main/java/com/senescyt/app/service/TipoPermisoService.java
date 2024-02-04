package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoPermiso;
import com.senescyt.app.repository.TipoPermisoRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPermisoService extends GenericServiceImpl<TipoPermiso, Long> implements GenericService<TipoPermiso, Long> {
    @Autowired
    TipoPermisoRepository tipoPermisoRepository;

    @Override
    public CrudRepository<TipoPermiso, Long> getDao() {
        return tipoPermisoRepository;
    }

    public List<TipoPermiso> getTipoPermisoByEstado(int est) {
        return tipoPermisoRepository.getTipoPermisoByEstado(est);
    }

    public List<TipoPermiso> searchTipopermiso(String search, int est) {
        return tipoPermisoRepository.searchTipopermiso(search, est);
    }

    public TipoPermiso getTipoPermsioById(Long id) {
        return tipoPermisoRepository.getTipoPermsioById(id);
    }
}

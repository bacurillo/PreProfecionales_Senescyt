package com.senescyt.app.service;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.repository.MotivoPermisoRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivoPermisoService extends GenericServiceImpl<MotivoPermiso, Long> implements GenericService<MotivoPermiso, Long> {

    @Autowired
    MotivoPermisoRepository motivoPermisoRepository;

    @Override
    public CrudRepository<MotivoPermiso, Long> getDao() {
        return motivoPermisoRepository;
    }

    public List<MotivoPermiso> getMotivosByEstado(int est) {
        return motivoPermisoRepository.getMotivosByEstado(est);
    }
}

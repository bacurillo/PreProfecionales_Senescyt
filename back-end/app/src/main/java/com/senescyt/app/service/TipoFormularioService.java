package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.TipoFormulario;
import com.senescyt.app.repository.TipoFormularioRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFormularioService extends GenericServiceImpl<TipoFormulario, Long> implements GenericService<TipoFormulario, Long> {
    @Autowired
    TipoFormularioRepository tipoFormularioRepository;

    @Override
    public CrudRepository<TipoFormulario, Long> getDao() {
        return tipoFormularioRepository;
    }

    public List<TipoFormulario> getTipoFormularioByEstado(int est) {
        return tipoFormularioRepository.getTipoFormularioByEstado(est);
    }
}

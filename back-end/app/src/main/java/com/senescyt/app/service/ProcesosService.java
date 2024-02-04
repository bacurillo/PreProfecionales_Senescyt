package com.senescyt.app.service;

import com.senescyt.app.model.Procesos;
import com.senescyt.app.repository.ProcesosRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesosService extends GenericServiceImpl<Procesos, Long> implements GenericService<Procesos, Long> {
    @Autowired
    ProcesosRepository procesosRepository;

    @Override
    public CrudRepository<Procesos, Long> getDao() {
        return procesosRepository;
    }

    public List<Procesos> getProcesosByEstado(int est) {
        return procesosRepository.getProcesosByEstado(est);
    }

    public List<Procesos> searchProcesos(String search, int est) {
        return procesosRepository.searchProcesos(search, est);
    }
}

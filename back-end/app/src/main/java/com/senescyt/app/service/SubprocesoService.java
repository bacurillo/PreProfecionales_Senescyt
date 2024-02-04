package com.senescyt.app.service;

import com.senescyt.app.model.Asistencia;
import com.senescyt.app.model.Ciudad;
import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.repository.SubprocesosRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprocesoService extends GenericServiceImpl<Subprocesos, Long> implements GenericService<Subprocesos, Long> {
    @Autowired
    SubprocesosRepository subprocesosRepository;

    @Override
    public CrudRepository<Subprocesos, Long> getDao() {
        return subprocesosRepository;
    }

    public List<Subprocesos> getSubprocesosByProcId(Long idSubproceso){
        return subprocesosRepository.getSubprocesosByProcId(idSubproceso);
    }

    public List<Subprocesos> getSubprocesosByProcEstado(int estproc,int estsub) {
        return subprocesosRepository.getSubprocesosByProcEstado(estproc,estsub);
    }

    public List<Subprocesos> searchSubprocesos(String search,int est) {
        return subprocesosRepository.searchSubprocesos(search,est);
    }
}

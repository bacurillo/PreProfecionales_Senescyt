package com.senescyt.app.service;

import com.senescyt.app.model.Periodo;
import com.senescyt.app.repository.PeriodoRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoService extends GenericServiceImpl<Periodo, Long> implements GenericService<Periodo, Long> {

    @Autowired
    PeriodoRepository periodoRepository;

    @Override
    public CrudRepository<Periodo, Long> getDao() {
        return periodoRepository;
    }

    public List<Periodo> getPeriodoByEstado(int est) {
        return periodoRepository.getPeriodoByEstado(est);
    }

    public List<Periodo> searchPeriodos(String search, int est){
        return periodoRepository.searchPeriodos(search,est);
    }
}

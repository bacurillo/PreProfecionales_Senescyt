package com.senescyt.app.service;

import com.senescyt.app.model.ValorHora;
import com.senescyt.app.model.Zonales;
import com.senescyt.app.repository.ZonalesRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ZonalesService extends GenericServiceImpl<Zonales, Long> implements GenericService<Zonales, Long> {

    @Autowired
    private ZonalesRepository zonalesRepository;

    @Override
    public CrudRepository<Zonales, Long> getDao() {
        return zonalesRepository;
    }

    public List<Zonales> getZonalesByEstado(int est) {
        return zonalesRepository.getZonalesByEstado(est);
    }

    public List<Zonales> searchZonales(String search, int est) {
        return zonalesRepository.searchZonales(search, est);
    }

    public Zonales getZonalById(Long id) {
        return zonalesRepository.getZonalById(id);
    }
}

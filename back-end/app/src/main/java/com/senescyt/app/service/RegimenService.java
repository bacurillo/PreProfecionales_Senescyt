package com.senescyt.app.service;

import com.senescyt.app.model.Regimen;
import com.senescyt.app.repository.RegimenRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RegimenService extends GenericServiceImpl<Regimen, Long> implements GenericService<Regimen, Long> {

    @Autowired
    RegimenRepository regimenRepository;

    @Override
    public CrudRepository<Regimen, Long> getDao() {
        return regimenRepository;
    }
}

package com.senescyt.app.service;

import com.senescyt.app.model.Ciudad;
import com.senescyt.app.model.Persona;
import com.senescyt.app.repository.CiudadRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService extends GenericServiceImpl<Ciudad, Long> implements GenericService<Ciudad, Long> {

    @Autowired
    CiudadRepository ciudadRepository;

    @Override
    public CrudRepository<Ciudad, Long> getDao() {
        return ciudadRepository;
    }

    public List<Ciudad> ciudadByProv(Long id) {
        return ciudadRepository.ciudadByProv(id);
    }
}

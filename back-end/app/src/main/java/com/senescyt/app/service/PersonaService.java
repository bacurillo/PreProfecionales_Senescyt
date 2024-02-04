package com.senescyt.app.service;

import com.senescyt.app.model.Persona;
import com.senescyt.app.model.Rol;
import com.senescyt.app.repository.PersonaRepository;
import com.senescyt.app.repository.RolRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService extends GenericServiceImpl<Persona, Long> implements GenericService<Persona, Long> {

    @Autowired
    public PersonaRepository personaRepository;

    @Override
    public CrudRepository<Persona, Long> getDao() {
        return personaRepository;
    }


    public boolean cedulaUnica(String ci) {
        int cont = personaRepository.cedulaUnica(ci.trim());

        if (cont > 0) {
            return false;
        } else {
            return true;
        }
    }
}

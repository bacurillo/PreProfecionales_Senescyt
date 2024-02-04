package com.senescyt.app.service;

import com.senescyt.app.model.Permisos;
import com.senescyt.app.model.Persona;
import com.senescyt.app.repository.PermisosRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService extends GenericServiceImpl<Permisos, Long> implements GenericService<Permisos, Long> {
    @Autowired
    PermisosRepository permisosRepository;

    @Override
    public CrudRepository<Permisos, Long> getDao() {
        return permisosRepository;
    }

    public List<Permisos> getPermisosByUsuId(int id) {
        return permisosRepository.getPermisosByUsuId(id);
    }

    public List<Permisos> getPermisosByIdJefe(int id) {
        return permisosRepository.getPermisosByIdJefe(id);
    }

    public List<Permisos> getPermisosForAdmin(int est) {
        return permisosRepository.getPermisosForAdmin(est);
    }

    public List<Permisos> searchPermisos(String search) {
        return permisosRepository.searchPermisos(search);
    }

    public Permisos getPermisoById(Long id) {
        return permisosRepository.getPermisoById(id);
    }


}

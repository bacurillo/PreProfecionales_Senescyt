package com.senescyt.app.service;

import com.senescyt.app.model.Institucion;
import com.senescyt.app.repository.InsitucionRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitucionService extends GenericServiceImpl<Institucion, Long> implements GenericService<Institucion, Long> {

    @Autowired
    InsitucionRepository insitucionRepository;

    @Override
    public CrudRepository<Institucion, Long> getDao() {
        return insitucionRepository;
    }


    public List<Institucion> getInstitucionByTipId(Long tipId) {
        return insitucionRepository.getInstitucionByTipId(tipId);
    }

    public List<Institucion> getInstitucionesByTipId(int tipid, int instid) {
        return insitucionRepository.getInstitucionesByTipId(tipid, instid);
    }

    public List<Institucion> getInstitucionesByEstado(int est){
        return insitucionRepository.getInstitucionesByEstado(est);
    }

    public Institucion getInstitucionById(Long id){
        return insitucionRepository.getInstitucionById(id);
    }

    public List<Institucion> searchInstitucion(String search,int est){
        return insitucionRepository.searchInstitucion(search,est);
    }
}

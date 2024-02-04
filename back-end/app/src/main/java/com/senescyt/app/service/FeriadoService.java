package com.senescyt.app.service;


import com.senescyt.app.model.Feriado;
import com.senescyt.app.model.Zonales;
import com.senescyt.app.repository.FeriadoRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeriadoService extends GenericServiceImpl<Feriado, Long> implements GenericService<Feriado, Long> {

    @Autowired
    FeriadoRepository feriadoRepository;

    @Override
    public CrudRepository<Feriado, Long> getDao() {
        return feriadoRepository;
    }

    public List<Feriado> getFeriadosByEstado(int est) {
        return feriadoRepository.getFeriadosByEstado(est);
    }

    public Feriado getFeriadosById(Long id) {
        return feriadoRepository.getFeriadosById(id);
    }

    public boolean isFeriado(String fecha) {
        int feriado= feriadoRepository.isFeriado(fecha);
        if(feriado!=0){
            return true;
        }else{
            return false;
        }
    }
}

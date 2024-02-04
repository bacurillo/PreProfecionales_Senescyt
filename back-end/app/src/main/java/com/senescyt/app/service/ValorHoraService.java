package com.senescyt.app.service;

import com.senescyt.app.model.TipoPermiso;
import com.senescyt.app.model.ValorHora;
import com.senescyt.app.repository.ValorHoraRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValorHoraService extends GenericServiceImpl<ValorHora,Long> implements GenericService<ValorHora,Long> {

    @Autowired
    ValorHoraRepository valorHoraRepository;

    @Override
    public CrudRepository<ValorHora, Long> getDao() {
        return valorHoraRepository;
    }

    public List<ValorHora> getValoresByEstado(int est) {
        return valorHoraRepository.getValoresByEstado(est);
    }

    public int valorUnico() {
        return valorHoraRepository.valorUnico();
    }
}

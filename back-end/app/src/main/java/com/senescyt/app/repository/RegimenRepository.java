package com.senescyt.app.repository;

import com.senescyt.app.model.Regimen;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenRepository extends GenericRepository<Regimen,Long> {
}

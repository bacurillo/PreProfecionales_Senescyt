package com.senescyt.app.repository;

import com.senescyt.app.model.Ciudad;
import com.senescyt.app.model.Provincia;
import com.senescyt.app.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends GenericRepository<Provincia,Long> {

}

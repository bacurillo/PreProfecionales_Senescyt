package com.senescyt.app.service;

import com.senescyt.app.model.Horarios;
import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Usuario;
import com.senescyt.app.repository.HorariosRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorariosService extends GenericServiceImpl<Horarios, Long> implements GenericService<Horarios, Long> {

    @Autowired
    HorariosRepository horariosRepository;

    @Override
    public CrudRepository<Horarios, Long> getDao() {
        return horariosRepository;
    }

    public List<Horarios> findByHora(String hora) {
        // Utiliza una Specification para buscar en todos los campos del modelo
        Specification<Horarios> spec = (root, query, criteriaBuilder) -> {
            String likePattern = "%" + hora + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("horNumHoras"), likePattern),
                    criteriaBuilder.like(root.get("horHoraIngreso"), likePattern),
                    criteriaBuilder.like(root.get("horHoraSalida"), likePattern),
                    criteriaBuilder.like(root.get("horHoraAlmuerzoInicio"), likePattern),
                    criteriaBuilder.like(root.get("horHoraAlmuerzoFin"), likePattern)
            );
        };

        // Ordenar los resultados si es necesario
        Sort sort = JpaSort.unsafe(Sort.Direction.ASC, "horId"); // Ordena por ID como ejemplo

        // Ejecutar la consulta
        return horariosRepository.findAll(spec, sort);
    }

    public List<Horarios> getProcesosByHora(int est) {
        return horariosRepository.getProcesosByHorario(est);
    }

    public Horarios getHorarioById(Long id) {
        return horariosRepository.getHorarioById(id);
    }

}

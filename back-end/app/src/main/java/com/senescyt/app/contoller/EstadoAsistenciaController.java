package com.senescyt.app.contoller;

import com.senescyt.app.model.EstadoAsistencia;
import com.senescyt.app.model.EstadoAsistencia;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.EstadoAsistenciaService;
import com.senescyt.app.service.EstadoAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/estadoasistencia")
public class EstadoAsistenciaController {
    @Autowired
    private EstadoAsistenciaService estadoAsistenciaService;

    @GetMapping("/read")
    public ResponseEntity<List<EstadoAsistencia>> read() {
        return new ResponseEntity<>(estadoAsistenciaService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EstadoAsistencia> create(@RequestBody EstadoAsistencia p) {
        return new ResponseEntity<>(estadoAsistenciaService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EstadoAsistencia> update(@PathVariable Long id, @RequestBody EstadoAsistencia p) {
        EstadoAsistencia EstadoAsistencia = estadoAsistenciaService.findById(id);
        if (EstadoAsistencia != null) {
            try {

                EstadoAsistencia.setEstEstado(p.isEstEstado());
                EstadoAsistencia.setEstNombre(p.getEstNombre());

                return new ResponseEntity<>(estadoAsistenciaService.save(EstadoAsistencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        estadoAsistenciaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

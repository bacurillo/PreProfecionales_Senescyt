package com.senescyt.app.contoller;

import com.senescyt.app.model.Puesto;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/puesto")
public class PuestoController {
    @Autowired
    private PuestoService puestoService;

    @GetMapping("/read")
    public ResponseEntity<List<Puesto>> read() {
        return new ResponseEntity<>(puestoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Puesto> create(@RequestBody Puesto p) {
        return new ResponseEntity<>(puestoService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Puesto> update(@PathVariable Long id, @RequestBody Puesto p) {
        Puesto Puesto = puestoService.findById(id);
        if (Puesto != null) {
            try {

                Puesto.setPueNombre(p.getPueNombre());

                return new ResponseEntity<>(puestoService.save(Puesto), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        puestoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

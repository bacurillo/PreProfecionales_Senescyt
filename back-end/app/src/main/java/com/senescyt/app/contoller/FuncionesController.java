package com.senescyt.app.contoller;

import com.senescyt.app.model.Funciones;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.FuncionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/funciones")
public class FuncionesController {
    @Autowired
    private FuncionesService funcionesService;

    @GetMapping("/read")
    public ResponseEntity<List<Funciones>> read() {
        return new ResponseEntity<>(funcionesService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Funciones> create(@RequestBody Funciones p) {
        p.setFunEstado(1);
        return new ResponseEntity<>(funcionesService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/getFuncionesByEstado")
    public ResponseEntity<List<Funciones>> getFuncionesByEstado(@RequestParam int est) {
        return new ResponseEntity<>(funcionesService.getFuncionesByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/searchFunciones")
    public ResponseEntity<List<Funciones>> searchFunciones(@RequestParam String search, @RequestParam int est) {
        return new ResponseEntity<>(funcionesService.searchFunciones(search, est), HttpStatus.OK);
    }

    @PutMapping("/updateEstFuncion")
    public ResponseEntity<Funciones> updateEst(@RequestParam Long id, @RequestParam int est) {
        Funciones funciones = funcionesService.findById(id);
        if (funciones != null) {
            try {

                funciones.setFunEstado(est);
                funcionesService.save(funciones);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Funciones> update(@PathVariable Long id, @RequestBody Funciones p) {
        Funciones Funciones = funcionesService.findById(id);
        if (Funciones != null) {
            try {

                Funciones.setFunNombre(p.getFunNombre());

                return new ResponseEntity<>(funcionesService.save(Funciones), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        funcionesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.senescyt.app.contoller;

import com.senescyt.app.model.*;
import com.senescyt.app.service.FeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/feriados")
public class FeriadoController {

    @Autowired
    private FeriadoService feriadoService;

    @GetMapping("/read")
    public ResponseEntity<List<Feriado>> read() {
        return new ResponseEntity<>(feriadoService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/isFeriado")
    public ResponseEntity<Boolean> isFeriado(@RequestParam String fecha) {
        return new ResponseEntity<Boolean>(feriadoService.isFeriado(fecha), HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<Feriado> create(@RequestBody Feriado f) {
        f.setFerEstado(1);
        return new ResponseEntity<>(feriadoService.save(f), HttpStatus.CREATED);
    }

    @GetMapping("/getFeriadosByEstado")
    public ResponseEntity<List<Feriado>> getFeriadosByEstado(@RequestParam int est) {
        return new ResponseEntity<>(feriadoService.getFeriadosByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/getFeriadosById")
    public ResponseEntity<Feriado> getFeriadosById(@RequestParam Long id) {
        return new ResponseEntity<>(feriadoService.getFeriadosById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Feriado> update(@PathVariable Long id, @RequestBody Feriado f) {
        Feriado feriado = feriadoService.findById(id);
        if (feriado != null) {
            try {

                feriado.setFerFechaInicio(f.getFerFechaInicio());
                feriado.setFerFechaFin(f.getFerFechaFin());

                return new ResponseEntity<>(feriadoService.save(feriado), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Feriado> updateEst(@RequestParam Long id, @RequestParam int est) {
        Feriado feriado = feriadoService.findById(id);
        if (feriado != null) {
            try {

                feriado.setFerEstado(est);
                feriadoService.save(feriado);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

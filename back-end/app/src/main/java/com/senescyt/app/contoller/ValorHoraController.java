package com.senescyt.app.contoller;


import com.senescyt.app.model.TipoPermiso;
import com.senescyt.app.model.ValorHora;
import com.senescyt.app.service.ValorHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/valorhora")
public class ValorHoraController {

    @Autowired
    ValorHoraService valorHoraService;

    @GetMapping("/read")
    public ResponseEntity<List<ValorHora>> read() {
        return new ResponseEntity<>(valorHoraService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ValorHora> create(@RequestBody ValorHora v) {
        v.setValorEstado(1);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        v.setValorFecha(timestamp);
        return new ResponseEntity<>(valorHoraService.save(v), HttpStatus.CREATED);
    }

    @GetMapping("/getValoresByEstado")
    public ResponseEntity<List<ValorHora>> getValoresByEstado(@RequestParam int est) {
        return new ResponseEntity<>(valorHoraService.getValoresByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/getNumeroDeRegistros")
    public ResponseEntity<Integer> getNumeroDeRegistros() {
        int numeroRegistros = valorHoraService.valorUnico();
        return new ResponseEntity<>(numeroRegistros, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ValorHora> update(@PathVariable Long id, @RequestBody ValorHora v) {
        ValorHora valor = valorHoraService.findById(id);
        if (valor != null) {
            try {

                valor.setValorHora(v.getValorHora());

                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                v.setValorFecha(timestamp);

                return new ResponseEntity<>(valorHoraService.save(valor), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<ValorHora> updateEst(@RequestParam Long id, @RequestParam int est) {
        ValorHora valor = valorHoraService.findById(id);
        if (valor != null) {
            try {

                valor.setValorEstado(est);
                valorHoraService.save(valor);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ValorHora> delete(@PathVariable Long id) {
        valorHoraService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

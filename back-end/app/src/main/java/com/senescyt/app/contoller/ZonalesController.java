package com.senescyt.app.contoller;

import com.senescyt.app.model.Horarios;
import com.senescyt.app.model.ValorHora;
import com.senescyt.app.model.Zonales;
import com.senescyt.app.service.ValorHoraService;
import com.senescyt.app.service.ZonalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/zonales")
public class ZonalesController {

    @Autowired
    ZonalesService zonalesService;

    @PostMapping("/create")
    public ResponseEntity<Zonales> create(@RequestBody Zonales z) {
        z.setZonEstado(1);

        return new ResponseEntity<>(zonalesService.save(z), HttpStatus.CREATED);
    }

    @GetMapping("/getZonalesByEstado")
    public ResponseEntity<List<Zonales>> getZonalesByEstado(@RequestParam int est) {
        return new ResponseEntity<>(zonalesService.getZonalesByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/getZonalById")
    public ResponseEntity<Zonales> getZonalById(@RequestParam Long id) {
        return new ResponseEntity<>(zonalesService.getZonalById(id), HttpStatus.OK);
    }

    @GetMapping("/searchZonales")
    public ResponseEntity<List<Zonales>> searchZonales(@RequestParam String search, @RequestParam int est) {
        return new ResponseEntity<>(zonalesService.searchZonales(search, est), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Zonales> update(@PathVariable Long id, @RequestBody Zonales z) {
        Zonales zonal = zonalesService.findById(id);
        if (zonal != null) {
            try {

                zonal.setZonCodigo(z.getZonCodigo());
                zonal.setZonNombre(z.getZonNombre());

                return new ResponseEntity<>(zonalesService.save(zonal), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Zonales> updateEst(@RequestParam Long id, @RequestParam int est) {
        Zonales zonales = zonalesService.findById(id);
        if (zonales != null) {
            try {

                zonales.setZonEstado(est);
                zonalesService.save(zonales);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

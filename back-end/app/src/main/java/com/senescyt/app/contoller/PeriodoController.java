package com.senescyt.app.contoller;

import com.senescyt.app.model.Periodo;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/periodo")
public class PeriodoController {
    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/read")
    public ResponseEntity<List<Periodo>> read() {
        return new ResponseEntity<>(periodoService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/searchPeriodos")
    public ResponseEntity<List<Periodo>> searchPeriodos(String search,int est) {
        return new ResponseEntity<>(periodoService.searchPeriodos(search,est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Periodo> create(@RequestBody Periodo p) {
        p.setPeriEstado(1);
        return new ResponseEntity<>(periodoService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/getPeriodoByEstado")
    public ResponseEntity<List<Periodo>> getPeriodoByEstado(@RequestParam int est) {
        return new ResponseEntity<>(periodoService.getPeriodoByEstado(est), HttpStatus.OK);
    }

    @PutMapping("/updateEstPeriodo")
    public ResponseEntity<Periodo> updateEst(@RequestParam Long id, @RequestParam int est) {
        Periodo periodo = periodoService.findById(id);
        if (periodo != null) {
            try {

                periodo.setPeriEstado(est);
                periodoService.save(periodo);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Periodo> update(@PathVariable Long id, @RequestBody Periodo p) {
        Periodo Periodo = periodoService.findById(id);
        if (Periodo != null) {
            try {

                Periodo.setPeriActual(p.getPeriActual());
                Periodo.setPeriAnterior(p.getPeriAnterior());
                Periodo.setDiasAnticipacion(p.getDiasAnticipacion());

                return new ResponseEntity<>(periodoService.save(Periodo), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        periodoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

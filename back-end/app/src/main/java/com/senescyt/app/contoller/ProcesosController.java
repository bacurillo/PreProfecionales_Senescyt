package com.senescyt.app.contoller;

import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Rol;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.Usuario;
import com.senescyt.app.service.ProcesosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/procesos")
public class ProcesosController {
    @Autowired
    private ProcesosService procesosService;

    @GetMapping("/read")
    public ResponseEntity<List<Procesos>> read() {
        return new ResponseEntity<>(procesosService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getProcesosByEstado")
    public ResponseEntity<List<Procesos>> getProcesosByEstado(@RequestParam int est) {
        return new ResponseEntity<>(procesosService.getProcesosByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/searchProcesos")
    public ResponseEntity<List<Procesos>> searchProcesos(@RequestParam String search, @RequestParam int est) {
        return new ResponseEntity<>(procesosService.searchProcesos(search, est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Procesos> create(@RequestBody Procesos p) {
        p.setProcEstado(1);
        return new ResponseEntity<>(procesosService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Procesos> update(@PathVariable Long id, @RequestBody Procesos p) {
        Procesos procesos = procesosService.findById(id);
        if (procesos != null) {
            try {

                procesos.setProcNombre(p.getProcNombre());

                return new ResponseEntity<>(procesosService.save(procesos), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Procesos> updateEst(@RequestParam Long id, @RequestParam int est) {
        Procesos procesos = procesosService.findById(id);
        if (procesos != null) {
            try {

                procesos.setProcEstado(est);
                procesosService.save(procesos);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        procesosService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

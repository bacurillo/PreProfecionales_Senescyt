package com.senescyt.app.contoller;

import com.senescyt.app.model.Ciudad;
import com.senescyt.app.model.Procesos;
import com.senescyt.app.model.Subprocesos;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.SubprocesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/subprocesos")
public class SubprocesosController {
    @Autowired
    private SubprocesoService subprocesosService;

    @GetMapping("/read")
    public ResponseEntity<List<Subprocesos>> read() {
        return new ResponseEntity<>(subprocesosService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getSubprocesosByProcId/{id}")
    public ResponseEntity<List<Subprocesos>> getSubprocesosByProcesosId(@PathVariable Long id) {
        return new ResponseEntity<>(subprocesosService.getSubprocesosByProcId(id), HttpStatus.OK);
    }

    @GetMapping("/searchSubprocesos")
    public ResponseEntity<List<Subprocesos>> searchSubprocesos(@RequestParam String search, @RequestParam int est) {
        return new ResponseEntity<>(subprocesosService.searchSubprocesos(search, est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Subprocesos> create(@RequestBody Subprocesos p) {
        p.setSubEstado(1);
        return new ResponseEntity<>(subprocesosService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/getSubprocesosByProcEstado")
    public ResponseEntity<List<Subprocesos>> getSubprocesosByProcEstado(@RequestParam int estproc, @RequestParam int estsub) {
        return new ResponseEntity<>(subprocesosService.getSubprocesosByProcEstado(estproc, estsub), HttpStatus.OK);
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Subprocesos> updateEst(@RequestParam Long id, @RequestParam int est) {
        Subprocesos subprocesos = subprocesosService.findById(id);
        if (subprocesos != null) {
            try {

                subprocesos.setSubEstado(est);
                subprocesosService.save(subprocesos);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subprocesos> update(@PathVariable Long id, @RequestBody Subprocesos p) {
        Subprocesos Subprocesos = subprocesosService.findById(id);
        if (Subprocesos != null) {
            try {

                Subprocesos.setSubNombre(p.getSubNombre());

                return new ResponseEntity<>(subprocesosService.save(Subprocesos), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        subprocesosService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

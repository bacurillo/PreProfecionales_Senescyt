package com.senescyt.app.contoller;


import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.service.MotivoPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/motivopermiso")
public class MotivoPermisoController {

    @Autowired
    private MotivoPermisoService motivoPermisoService;

    @GetMapping("/read")
    public ResponseEntity<List<MotivoPermiso>> read() {
        return new ResponseEntity<>(motivoPermisoService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getMotivosByEstado")
    public ResponseEntity<List<MotivoPermiso>> getMotivosByEstado(@RequestParam int est) {
        return new ResponseEntity<>(motivoPermisoService.getMotivosByEstado(est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MotivoPermiso> create(@RequestBody MotivoPermiso m) {
        m.setMotEstado(1);
        return new ResponseEntity<>(motivoPermisoService.save(m), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MotivoPermiso> update(@PathVariable Long id, @RequestBody MotivoPermiso m) {
        MotivoPermiso motivoPermiso = motivoPermisoService.findById(id);
        if (motivoPermiso != null) {
            try {

                motivoPermiso.setMotNombre(m.getMotNombre());

                return new ResponseEntity<>(motivoPermisoService.save(motivoPermiso), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<TipoInstitucion> updateEst(@RequestParam Long id, @RequestParam int est) {
        MotivoPermiso motivoPermiso = motivoPermisoService.findById(id);
        if (motivoPermiso != null) {
            try {

                motivoPermiso.setMotEstado(est);
                motivoPermisoService.save(motivoPermiso);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MotivoPermiso> delete(@PathVariable Long id) {
        motivoPermisoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

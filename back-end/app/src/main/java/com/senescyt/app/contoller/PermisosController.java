package com.senescyt.app.contoller;

import com.senescyt.app.model.Permisos;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/permisos")
public class PermisosController {
    @Autowired
    private PermisoService permisosService;


    @PostMapping("/create")
    public ResponseEntity<Permisos> create(@RequestBody Permisos p) {
        Date date = new Date();
        Date fechahoy = new Date(date.getTime());
        p.setPermFechaEmision(fechahoy);
        return new ResponseEntity<>(permisosService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/getPermisosByUsuId")
    public ResponseEntity<List<Permisos>> getPermisosByUsuId(@RequestParam int id) {
        return new ResponseEntity<>(permisosService.getPermisosByUsuId(id), HttpStatus.OK);
    }

    @GetMapping("/searchPermisos")
    public ResponseEntity<List<Permisos>> searchPermisos(@RequestParam String search) {
        return new ResponseEntity<>(permisosService.searchPermisos(search), HttpStatus.OK);
    }

    @GetMapping("/getPermisosByIdJefe")
    public ResponseEntity<List<Permisos>> getPermisosByIdJefe(@RequestParam int id) {
        return new ResponseEntity<>(permisosService.getPermisosByIdJefe(id), HttpStatus.OK);
    }

        @GetMapping("/getPermisoById")
    public ResponseEntity<Permisos> getPermisoById(@RequestParam Long id) {
        return new ResponseEntity<>(permisosService.getPermisoById(id), HttpStatus.OK);
    }

    @GetMapping("/getPermisosForAdmin")
    public ResponseEntity<List<Permisos>> getPermisosForAdmin(@RequestParam int est) {
        return new ResponseEntity<>(permisosService.getPermisosForAdmin(est), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Permisos> update(@RequestParam Long id, @RequestBody String p) {

        Permisos permisos = permisosService.findById(id);
        if (permisos != null) {
            try {
                permisos.setPermDocumento(p);
                permisosService.save(permisos);
                return new ResponseEntity<>(permisos, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Permisos> updateEst(@RequestParam Long id, @RequestParam int est) {
        Permisos permisos = permisosService.findById(id);
        if (permisos != null) {
            try {

                permisos.setPermEstado(est);
                permisosService.save(permisos);
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
        permisosService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

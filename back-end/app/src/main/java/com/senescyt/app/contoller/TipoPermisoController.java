package com.senescyt.app.contoller;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.model.TipoPermiso;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.TipoPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/tipopermiso")
public class TipoPermisoController {
    @Autowired
    private TipoPermisoService tipPermisoService;

    @GetMapping("/read")
    public ResponseEntity<List<TipoPermiso>> read() {
        return new ResponseEntity<>(tipPermisoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoPermiso> create(@RequestBody TipoPermiso p) {
        p.setTiPeEstado(1);
        return new ResponseEntity<>(tipPermisoService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/getTipoPermisoByEstado")
    public ResponseEntity<List<TipoPermiso>> getTipoPermisoByEstado(@RequestParam int est) {
        return new ResponseEntity<>(tipPermisoService.getTipoPermisoByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/searchTipopermiso")
    public ResponseEntity<List<TipoPermiso>> searchTipopermiso(@RequestParam String search, @RequestParam int est) {
        return new ResponseEntity<>(tipPermisoService.searchTipopermiso(search, est), HttpStatus.OK);
    }

    @GetMapping("/getTipoPermsioById")
    public ResponseEntity <TipoPermiso> getTipoPermsioById(@RequestParam Long id) {
        return new ResponseEntity<>(tipPermisoService.getTipoPermsioById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoPermiso> update(@PathVariable Long id, @RequestBody TipoPermiso p) {
        TipoPermiso tipoPermiso = tipPermisoService.findById(id);
        if (tipoPermiso != null) {
            try {

                tipoPermiso.setTiPeNombre(p.getTiPeNombre());
                tipoPermiso.setTiPeDescripcion(p.getTiPeDescripcion());

                return new ResponseEntity<>(tipPermisoService.save(tipoPermiso), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<TipoPermiso> updateEst(@RequestParam Long id, @RequestParam int est) {
        TipoPermiso tipoPermiso = tipPermisoService.findById(id);
        if (tipoPermiso != null) {
            try {

                tipoPermiso.setTiPeEstado(est);
                tipPermisoService.save(tipoPermiso);
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
        tipPermisoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

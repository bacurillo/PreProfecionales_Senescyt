package com.senescyt.app.contoller;

import com.senescyt.app.model.*;
import com.senescyt.app.service.TipoInstitucionService;
import com.senescyt.app.service.TipoPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/tipoinstitucion")
public class TipoInstitucionController {

    @Autowired
    private TipoInstitucionService tipoInstitucionService;

    @GetMapping("/read")
    public ResponseEntity<List<TipoInstitucion>> read() {
        return new ResponseEntity<>(tipoInstitucionService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getTipoInstitucionByEstado")
    public ResponseEntity<List<TipoInstitucion>> getTipoInstitucionByEstado(@RequestParam int est) {
        return new ResponseEntity<>(tipoInstitucionService.getTipoInstitucionByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/searchTipoInstitucion")
    public ResponseEntity<List<TipoInstitucion>> searchTipoInstitucion(@RequestParam String search,@RequestParam int est) {
        return new ResponseEntity<>(tipoInstitucionService.searchTipoInstitucion(search,est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoInstitucion> create(@RequestBody TipoInstitucion ti) {
        ti.setTipEstado(1);
        return new ResponseEntity<>(tipoInstitucionService.save(ti), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoInstitucion> update(@PathVariable Long id, @RequestBody TipoInstitucion ti) {
        TipoInstitucion tipoInstitucion = tipoInstitucionService.findById(id);
        if (tipoInstitucion != null) {
            try {

                tipoInstitucion.setTipNombre(ti.getTipNombre());

                return new ResponseEntity<>(tipoInstitucionService.save(tipoInstitucion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<TipoInstitucion> updateEst(@RequestParam Long id, @RequestParam int est) {
        TipoInstitucion tipoInstitucion = tipoInstitucionService.findById(id);
        if (tipoInstitucion != null) {
            try {

                tipoInstitucion.setTipEstado(est);
                tipoInstitucionService.save(tipoInstitucion);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TipoInstitucion> delete(@PathVariable Long id) {
        tipoInstitucionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.senescyt.app.contoller;

import com.senescyt.app.model.*;
import com.senescyt.app.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/institucion")
public class InstitucionController {


    @Autowired
    private InstitucionService institucionService;

    @GetMapping("/read")
    public ResponseEntity<List<Institucion>> read() {
        return new ResponseEntity<>(institucionService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getInstitucionByTipId/{id}")
    public ResponseEntity<List<Institucion>> getInstitucionByTipId(@PathVariable Long id) {
        return new ResponseEntity<>(institucionService.getInstitucionByTipId(id), HttpStatus.OK);
    }

    @GetMapping("/getInstitucionesByTipId")
    public ResponseEntity<List<Institucion>> getInstitucionesByTipId(@RequestParam int tipid, @RequestParam int instid) {
        return new ResponseEntity<>(institucionService.getInstitucionesByTipId(tipid, instid), HttpStatus.OK);
    }

    @GetMapping("/getInstitucionesByEstado")
    public ResponseEntity<List<Institucion>> getInstitucionesByEstado(@RequestParam int est) {
        return new ResponseEntity<>(institucionService.getInstitucionesByEstado(est), HttpStatus.OK);
    }

    @GetMapping("/getInstitucionById")
    public ResponseEntity<Institucion> getInstitucionById(@RequestParam Long id) {
        return new ResponseEntity<>(institucionService.getInstitucionById(id), HttpStatus.OK);
    }

    @GetMapping("/searchInstitucion")
    public ResponseEntity<List<Institucion>> searchInstitucion(@RequestParam String search,@RequestParam int est) {
        return new ResponseEntity<>(institucionService.searchInstitucion(search,est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Institucion> create(@RequestBody Institucion p) {
        p.setInstEstado(1);
        return new ResponseEntity<>(institucionService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Institucion> update(@PathVariable Long id, @RequestBody Institucion p) {
        Institucion institucion = institucionService.findById(id);
        if (institucion != null) {
            try {

                institucion.setInstNombre(p.getInstNombre());
                institucion.setInstDireccion(p.getInstDireccion());
                institucion.setInstCodigo(p.getInstCodigo());
                institucion.setInstReferencia(p.getInstReferencia());

                return new ResponseEntity<>(institucionService.save(institucion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Institucion> updateEst(@RequestParam Long id, @RequestParam int est) {
        Institucion institucion = institucionService.findById(id);
        if (institucion != null) {
            try {

                institucion.setInstEstado(est);
                institucionService.save(institucion);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Institucion> delete(@PathVariable Long id) {
        institucionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

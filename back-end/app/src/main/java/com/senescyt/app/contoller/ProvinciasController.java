package com.senescyt.app.contoller;

import com.senescyt.app.model.Provincia;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/provincias")
public class ProvinciasController {
    @Autowired
    private ProvinciaService provinciasService;

    @GetMapping("/read")
    public ResponseEntity<List<Provincia>> read() {
        return new ResponseEntity<>(provinciasService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Provincia> create(@RequestBody Provincia p) {
        return new ResponseEntity<>(provinciasService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Provincia> update(@PathVariable Long id, @RequestBody Provincia p) {
        Provincia Provincia = provinciasService.findById(id);
        if (Provincia != null) {
            try {

                Provincia.setProNombre(p.getProNombre());

                return new ResponseEntity<>(provinciasService.save(Provincia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        provinciasService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

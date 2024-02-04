package com.senescyt.app.contoller;

import com.senescyt.app.model.CargoUsuario;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.CargoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/cargousuario")
public class CargoUsuarioController {
    @Autowired
    private CargoUsuarioService cargousuService;

    @GetMapping("/read")
    public ResponseEntity<List<CargoUsuario>> read() {
        return new ResponseEntity<>(cargousuService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CargoUsuario> create(@RequestBody CargoUsuario p) {
        return new ResponseEntity<>(cargousuService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CargoUsuario> update(@PathVariable Long id, @RequestBody CargoUsuario p) {
        CargoUsuario CargoUsuario = cargousuService.findById(id);
        if (CargoUsuario != null) {
            try {

                CargoUsuario.setUsuId(p.getUsuId());
                CargoUsuario.setPueId(p.getPueId());

                return new ResponseEntity<>(cargousuService.save(CargoUsuario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        cargousuService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

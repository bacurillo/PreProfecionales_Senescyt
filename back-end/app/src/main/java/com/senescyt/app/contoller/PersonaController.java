/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senescyt.app.contoller;

import com.senescyt.app.model.Persona;
import com.senescyt.app.model.Rol;
import com.senescyt.app.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author ALEJO PC
 */
@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/read")
    public ResponseEntity<List<Persona>> read() {
        System.out.println("\n\n\n\n\n "+ personaService.findByAll().size()+ "\n\n\n\n");
        return new ResponseEntity<>(personaService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/cedulaUnica")
    public ResponseEntity<Boolean> cedulaUnica(@RequestParam String ci) {
        return new ResponseEntity<>(personaService.cedulaUnica(ci), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Persona> create(@RequestBody Persona p) {
        return new ResponseEntity<>(personaService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Persona> update(@RequestParam Long id, @RequestBody Persona p) {
        Persona persona = personaService.findById(id);
        if (persona != null) {
            try {

                persona.setPerApellido(p.getPerApellido());
                persona.setPerCedula(p.getPerCedula());
                persona.setPerDireccion(p.getPerDireccion());
                persona.setPerTelefono(p.getPerTelefono());
                persona.setPerNombre(p.getPerNombre());
                persona.setPerFechaNacimiento(p.getPerFechaNacimiento());

                return new ResponseEntity<>(personaService.save(persona), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Rol> delete(@RequestParam Long id) {
        personaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

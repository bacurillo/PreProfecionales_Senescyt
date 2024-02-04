package com.senescyt.app.contoller;

import com.senescyt.app.model.*;
import com.senescyt.app.service.HorariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/horarios")
public class HorariosController {
    @Autowired
    private HorariosService horariosService;

    @GetMapping("/read")
    public ResponseEntity<List<Horarios>> read() {
        return new ResponseEntity<>(horariosService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Horarios> create(@RequestBody Horarios h) {
        h.setHorEstado(1);
        return new ResponseEntity<>(horariosService.save(h), HttpStatus.CREATED);
    }

    @GetMapping("/getHorarioById")
    public ResponseEntity<Horarios> getHorarioById(@RequestParam Long id) {
        return new ResponseEntity<>(horariosService.getHorarioById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Horarios> update(@PathVariable Long id, @RequestBody Horarios p) {
        Horarios Horarios = horariosService.findById(id);
        if (Horarios != null) {
            try {

                Horarios.setHorHoraIngresoDia(p.getHorHoraIngresoDia());
                Horarios.setHorHoraSalidaDia(p.getHorHoraSalidaDia());
                Horarios.setHorNumHoras(p.getHorNumHoras());
                Horarios.setHorHorasParaAlmuerzo(p.getHorHorasParaAlmuerzo());
                Horarios.setHorHoraIngresoTarde(p.getHorHoraIngresoTarde());
                Horarios.setHorHoraSalidaTarde(p.getHorHoraSalidaTarde());


                Horarios.setHorNumHoras(p.getHorNumHoras());

                return new ResponseEntity<>(horariosService.save(Horarios), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Horarios> updateEst(@RequestParam Long id, @RequestParam int est) {
        Horarios horarios = horariosService.findById(id);
        if (horarios != null) {
            try {

                horarios.setHorEstado(est);
                horariosService.save(horarios);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/searchByHour/{hora}")
    public ResponseEntity<List<Horarios>> searchByHour(@PathVariable String hora) {
        List<Horarios> horarios = horariosService.findByHora(hora);
        if (horarios.isEmpty()) {
            // Si no se encuentran horarios con la hora especificada, devuelve un HttpStatus.NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    @GetMapping("/getProcesosByHorarios")
    public ResponseEntity<List<Horarios>> getProcesosByHorarios(@RequestParam int est) {
        return new ResponseEntity<>(horariosService.getProcesosByHora(est), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Horarios> delete(@PathVariable Long id) {
        horariosService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

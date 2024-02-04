package com.senescyt.app.contoller;

import com.senescyt.app.model.MotivoPermiso;
import com.senescyt.app.model.TipoFormulario;
import com.senescyt.app.model.Rol;
import com.senescyt.app.model.TipoInstitucion;
import com.senescyt.app.service.TipoFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/tipoformulario")
public class TipoFormularioController {
    @Autowired
    private TipoFormularioService tipFormularioService;

    @GetMapping("/read")
    public ResponseEntity<List<TipoFormulario>> read() {
        return new ResponseEntity<>(tipFormularioService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getTipoFormularioByEstado")
    public ResponseEntity<List<TipoFormulario>> getTipoFormularioByEstado(@RequestParam int est) {
        return new ResponseEntity<>(tipFormularioService.getTipoFormularioByEstado(est), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoFormulario> create(@RequestBody TipoFormulario p) {
        p.setTiFoEstado(1);
        return new ResponseEntity<>(tipFormularioService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoFormulario> update(@PathVariable Long id, @RequestBody TipoFormulario p) {
        TipoFormulario TipoFormulario = tipFormularioService.findById(id);
        if (TipoFormulario != null) {
            try {

                TipoFormulario.setTiFoNombre(p.getTiFoNombre());

                return new ResponseEntity<>(tipFormularioService.save(TipoFormulario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<TipoFormulario> updateEst(@RequestParam Long id, @RequestParam int est) {
        TipoFormulario tipoFormulario = tipFormularioService.findById(id);
        if (tipoFormulario != null) {
            try {

                tipoFormulario.setTiFoEstado(est);
                tipFormularioService.save(tipoFormulario);
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
        tipFormularioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.senescyt.app.contoller;

import com.senescyt.app.model.Permisos;
import com.senescyt.app.model.Regimen;
import com.senescyt.app.service.RegimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/regimen")
public class RegimenController {

    @Autowired
    RegimenService regimenService;

    @GetMapping("/read")
    public ResponseEntity<List<Regimen>> read() {
        return new ResponseEntity<>(regimenService.findByAll(), HttpStatus.OK);
    }
}

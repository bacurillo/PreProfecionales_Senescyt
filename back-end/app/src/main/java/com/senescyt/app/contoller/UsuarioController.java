package com.senescyt.app.contoller;

import com.senescyt.app.Security.Auth.AuthResponse;
import com.senescyt.app.Security.Auth.AuthService;
import com.senescyt.app.Security.Auth.RegisterRequest;
import com.senescyt.app.model.*;
import com.senescyt.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private final AuthService authService;
    @Autowired
    private RolService rolService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private SubprocesoService subprocesoService;

    @Autowired
    private ZonalesService zonalesService;

    @Autowired
    private InstitucionService institucionService;
    @Autowired
    private FuncionesService funcionesService;

    @Autowired
    private RegimenService regimenService;

    @Autowired
    private HorariosService horariosService;

    private final PasswordEncoder passwordEncoder;

    public UsuarioController(AuthService authService, RolService rolService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/read")
    public ResponseEntity<List<Usuario>> read() {
        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getJefesByRolId")
    public ResponseEntity<List<Usuario>> getJefesByRolId(@RequestParam Long id) {
        return new ResponseEntity<>(usuarioService.getJefesByRolId(id), HttpStatus.OK);
    }

    @GetMapping("/searchUserId")
    public ResponseEntity<Usuario> searchUserId(@RequestParam Long id) {
        return new ResponseEntity<>(usuarioService.findByUsuId(id), HttpStatus.OK);
    }

    @GetMapping("/allUsersData")
    public ResponseEntity<List<Usuario>> allUsersData(@RequestParam int est) {
        List<Object[]> userData = usuarioService.allUsersData(est);
        List<Usuario> users = new ArrayList<>();

        for (Object[] data : userData) {
            Usuario user = new Usuario();
            user.setUsuId((Long) data[0]);
            user.setUsuCorreo((String) data[1]);
            user.setUsuEstado((int) data[2]);
            user.setUsuFechaRegistro((Timestamp) data[3]);
            user.setUsuNombreUsuario((String) data[4]);

            Long funId = (Long) data[5];
            user.setFunId(funcionesService.findById(funId));

            Long insId = (Long) data[6];
            user.setInsId(institucionService.findById(insId));


            Long subProcId = (Long) data[7];
            user.setSubId(subprocesoService.findById(subProcId));

            Long rolId = (Long) data[8];
            user.setRolId(rolService.findById(rolId));

            Long personaId = (Long) data[9];
            user.setUsuPerId(personaService.findById(personaId));

            user.setFoto((String) data[10]);
            user.setTitulo((String) data[11]);

            Long regId = (Long) data[12];
            user.setRegId(regimenService.findById(regId));

            Long zonId = (Long) data[13];
            user.setZonId(zonalesService.findById(zonId));

            Long horId = (Long) data[14];
            user.setHorId(horariosService.findById(horId));

            user.setUsuSaldoVacacional((double) data[15]);

            user.setUsuIdJefe((Long) data[16]);
            user.setUsuIdLector((Long) data[17]);

//            user.setUsuContrasena(""); // Establecer contrasena como cadena vacía
            users.add(user);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/searchUsersData")
    public ResponseEntity<List<Usuario>> searchUsersData(@RequestParam String search, @RequestParam int est) {
        List<Object[]> userData = usuarioService.searchUsersData(search, est);
        List<Usuario> users = new ArrayList<>();

        for (Object[] data : userData) {
            Usuario user = new Usuario();
            user.setUsuId((Long) data[0]);
            user.setUsuCorreo((String) data[1]);
            user.setUsuEstado((int) data[2]);
            user.setUsuFechaRegistro((Timestamp) data[3]);
            user.setUsuNombreUsuario((String) data[4]);

            Long funId = (Long) data[5];
            user.setFunId(funcionesService.findById(funId));
//
            Long insId = (Long) data[6];
            user.setInsId(institucionService.findById(insId));

            Long procId = (Long) data[7];
            user.setSubId(subprocesoService.findById(procId));

            Long rolId = (Long) data[8];
            user.setRolId(rolService.findById(rolId));

            Long personaId = (Long) data[9];
            user.setUsuPerId(personaService.findById(personaId));

            user.setFoto((String) data[10]);
            user.setTitulo((String) data[11]);

            Long regId = (Long) data[12];
            user.setRegId(regimenService.findById(regId));

            Long zonId = (Long) data[13];
            user.setZonId(zonalesService.findById(zonId));

            Long horId = (Long) data[14];
            user.setHorId(horariosService.findById(horId));

            user.setUsuSaldoVacacional((double) data[15]);

            user.setUsuIdJefe((Long) data[16]);
            user.setUsuIdLector((Long) data[17]);

//            user.setUsuContrasena(""); // Establecer contrasena como cadena vacía
            users.add(user);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/searchUsersCI")
    public ResponseEntity<List<Usuario>> searchUsersCI(@RequestParam String search, @RequestParam int est) {
        List<Object[]> userData = usuarioService.searchUsersCI(search, est);
        List<Usuario> users = new ArrayList<>();

        for (Object[] data : userData) {
            Usuario user = new Usuario();
            user.setUsuId((Long) data[0]);

            user.setUsuEstado((int) data[1]);

            Long perId = (Long) data[2];
            Persona p = personaService.findById(perId);

            Persona persona = new Persona();
            persona.setPerNombre(p.getPerNombre());
            persona.setPerApellido(p.getPerApellido());
            persona.setPerFechaNacimiento(p.getPerFechaNacimiento());
            persona.setCiuId(p.getCiuId());

            user.setUsuPerId(persona);

            users.add(user);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/usuarioUnico")
    public ResponseEntity<Boolean> usuarioUnico(@RequestParam String user) {
        return new ResponseEntity<>(usuarioService.usuarioUnico(user), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> create(@RequestBody Usuario u) {
        return new ResponseEntity<>(usuarioService.save(u), HttpStatus.CREATED);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> update(@RequestParam Long id, @RequestBody Usuario u) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            try {

                usuario.setUsuEstado(u.getUsuEstado());
                usuario.setUsuNombreUsuario(u.getUsuNombreUsuario());
                usuario.setRolId(u.getRolId());
                usuario.setUsuCorreo(u.getUsuCorreo());
                usuario.setFunId(u.getFunId());
                usuario.setFoto(u.getFoto());
                usuario.setTitulo(u.getTitulo());
                usuario.setInsId(u.getInsId());
                usuario.setSubId(u.getSubId());
                usuario.setRegId(u.getRegId());
                usuario.setZonId(u.getZonId());
                usuario.setHorId(u.getHorId());
                usuario.setUsuSaldoVacacional(u.getUsuSaldoVacacional());
                if(!u.getUsuContrasena().isEmpty()){
                    usuario.setUsuContrasena(passwordEncoder.encode(u.getUsuContrasena()));
                }


                return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEst")
    public ResponseEntity<Usuario> updateEst(@RequestParam Long id, @RequestParam int est) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            try {
//                usuario.setUsuEstado(est);
//                return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
                usuario.setUsuEstado(est);
                usuarioService.save(usuario);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateSaldo")
    public ResponseEntity<Usuario> updateEst(@RequestParam Long id, @RequestParam double saldo) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            try {

                usuario.setUsuSaldoVacacional(saldo);
                usuarioService.save(usuario);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Usuario> delete(@RequestParam Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

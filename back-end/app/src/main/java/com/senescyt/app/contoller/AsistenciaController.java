package com.senescyt.app.contoller;

import com.senescyt.app.model.*;
import com.senescyt.app.service.AsistenciaService;
import com.senescyt.app.service.FeriadoService;
import com.senescyt.app.service.HorariosService;
import com.senescyt.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/snc/asistencia")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HorariosService horariosService;

    @Autowired
    private FeriadoService feriadoService;

    // Crear un nuevo formato para obtener solo la hora
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

    @GetMapping("/read")
    public ResponseEntity<List<Asistencia>> read() {
        return new ResponseEntity<>(asistenciaService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/historialArchivos")
    public ResponseEntity<List<Map<String, Object>>> historialArchivos() {
        List<Map<String, Object>> hist = new ArrayList<>();
        List<Object[]> objs = asistenciaService.historialArchivos();

        for (Object[] data : objs) {
            Map<String, Object> obj = new HashMap<>();

            obj.put("indice", data[0]);
            obj.put("nombreArchivo", data[1]);
            obj.put("fechaArchivo", data[2]);
            obj.put("cantidadRegistros", data[3]);
            Long usuId = (Long) data[4];
            Usuario user = usuarioService.findByUsuId(usuId);
//            user.setUsuContrasena("");
            obj.put("userId", user);

            hist.add(obj);
        }

        return new ResponseEntity<>(hist, HttpStatus.OK);
    }

    @GetMapping("/historialArchivosSearch")
    public ResponseEntity<List<Map<String, Object>>> historialArchivosSearch(@RequestParam String fechaMin, @RequestParam String fechaMax, @RequestParam String nombre) {
        List<Map<String, Object>> hist = new ArrayList<>();
        List<Object[]> objs = asistenciaService.historialArchivosSearch(fechaMin, fechaMax, nombre);

        for (Object[] data : objs) {
            Map<String, Object> obj = new HashMap<>();

            obj.put("indice", data[0]);
            obj.put("nombreArchivo", data[1]);
            obj.put("fechaArchivo", data[2]);
            obj.put("cantidadRegistros", data[3]);
            Long usuId = (Long) data[4];
            Usuario user = usuarioService.findByUsuId(usuId);
//            user.setUsuContrasena("");
            obj.put("userId", user);

            hist.add(obj);
        }

        return new ResponseEntity<>(hist, HttpStatus.OK);
    }

    @GetMapping("/asistenciaSearch")
    public ResponseEntity<List<Map<String, Object>>> asistenciaSearch(@RequestParam String fechaMin, @RequestParam String fechaMax, @RequestParam String search) {
        List<Map<String, Object>> hist = new ArrayList<>();
        List<Object[]> objs = asistenciaService.asistenciaSearch(fechaMin, fechaMax, search);

        for (Object[] data : objs) {
            Map<String, Object> obj = new HashMap<>();
            Long asisId = (Long) data[0];
            Asistencia asistencia = asistenciaService.findById(asisId);
            obj.put("asisId", asistencia);

            Long usuId = (Long) data[1];
            Usuario user = usuarioService.findByUsuId(usuId);
//            user.setUsuContrasena("");
            obj.put("userId", user);

            hist.add(obj);
        }

        return new ResponseEntity<>(hist, HttpStatus.OK);
    }

    @GetMapping("/miAsistencia")
    public ResponseEntity<List<Map<String, Object>>> miAsistencia(@RequestParam Long usuId, @RequestParam String fechaMin, @RequestParam String fechaMax) {
        List<Map<String, Object>> hist = new ArrayList<>();
        List<Object[]> objs = asistenciaService.miAsistencia(usuId, fechaMin, fechaMax);

        for (Object[] data : objs) {
            Map<String, Object> obj = new HashMap<>();
            Long asisId = (Long) data[0];
            Asistencia asistencia = asistenciaService.findById(asisId);
            obj.put("asisId", asistencia);

            Long userId = (Long) data[1];
            Usuario user = usuarioService.findByUsuId(userId);
//            user.setUsuContrasena("");
            obj.put("userId", user);

            hist.add(obj);
        }

        return new ResponseEntity<>(hist, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Asistencia> create(@RequestBody Asistencia p) {
        return new ResponseEntity<>(asistenciaService.save(p), HttpStatus.CREATED);
    }

    @PostMapping("/saveList")
    public ResponseEntity<List<Asistencia>> createList(@RequestBody List<Asistencia> asistencias) {

        for (Asistencia asistencia : asistencias ){

            if(usuarioService.horarioUser(asistencia.getAsisNoLector()).length != 0){

                if(feriadoService.isFeriado(asistencia.getAsisFechaHora().split(" ")[0])){
                    asistencia.setAsisEstadoStr("Feriado");
                }else {

                    Horarios horarios = horariosService.findById((Long) usuarioService.horarioUser(asistencia.getAsisNoLector())[0]);

                    LocalTime horaAsistencia = LocalTime.parse(obtenerHora(asistencia.getAsisFechaHora()));
                    LocalTime ingresoDia = LocalTime.parse(horarios.getHorHoraIngresoDia()).plusMinutes(30);
                    LocalTime salidaDia = LocalTime.parse(horarios.getHorHoraIngresoDia());
                    LocalTime ingresoTarde = LocalTime.parse(horarios.getHorHoraIngresoDia()).plusMinutes(30);
                    LocalTime salidaTarde = LocalTime.parse(horarios.getHorHoraIngresoDia());


                    switch (asistencia.getAsisEstado().trim()) {
                        case "M/Ent":
                            if (horaAsistencia.isAfter(ingresoDia)) {
                                asistencia.setAsisEstadoStr("Ingreso Atrasado");
                            } else if (horaAsistencia.equals(ingresoDia)) {
                                asistencia.setAsisEstadoStr("Ingreso Puntual");
                            } else {
                                asistencia.setAsisEstadoStr("Ingreso Anticipado");
                            }
                            break;
                        case "M/Sal":
                            // Comparar las horas de salida y asistencia
                            if (horaAsistencia.isAfter(salidaDia)) {
                                asistencia.setAsisEstadoStr("Salida Retrasada");
                            } else if (horaAsistencia.equals(salidaDia)) {
                                asistencia.setAsisEstadoStr("Salida Puntual");
                            } else {
                                asistencia.setAsisEstadoStr("Salida Temprana");
                            }
                            break;
                        case "T/Ent":
                            if (horaAsistencia.isAfter(ingresoTarde)) {
                                asistencia.setAsisEstadoStr("Ingreso Atrasado");
                            } else if (horaAsistencia.equals(ingresoTarde)) {
                                asistencia.setAsisEstadoStr("Ingreso Puntual");
                            } else {
                                asistencia.setAsisEstadoStr("Ingreso Anticipado");
                            }
                            break;
                        case "T/Sal":
//                case "T/Sal":
                            if (horaAsistencia.isAfter(salidaTarde)) {
                                asistencia.setAsisEstadoStr("Salida Retrasada");
                            } else if (horaAsistencia.equals(salidaTarde)) {
                                asistencia.setAsisEstadoStr("Salida Puntual");
                            } else {
                                asistencia.setAsisEstadoStr("Salida Temprana");
                            }
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                }
            }


        }

        return new ResponseEntity<>(asistenciaService.saveAll(asistencias), HttpStatus.CREATED);
    }

    public String obtenerHora(String fechaHoraStr) {

        // Crear un objeto SimpleDateFormat para parsear la cadena
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            // Parsear la cadena a un objeto Date
            Date fechaHora = sdf.parse(fechaHoraStr);

            // Crear un nuevo formato para obtener solo la hora
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            // Obtener la hora como cadena
            return formatoHora.format(fechaHora);

        } catch (ParseException e) {
            e.printStackTrace();
            // Manejar la excepción en caso de un formato de fecha y hora incorrecto
            return null; // o lanzar una excepción personalizada según tu necesidad
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable Long id, @RequestBody Asistencia p) {
        Asistencia Asistencia = asistenciaService.findById(id);
        if (Asistencia != null) {
            try {

                Asistencia.setAsisNombre(p.getAsisNombre());
                Asistencia.setAsisDpto(p.getAsisDpto());
                Asistencia.setAsisNoLector(p.getAsisNoLector());
                Asistencia.setAsisFechaHora(p.getAsisFechaHora());
                Asistencia.setAsisEstado(p.getAsisEstado());
                Asistencia.setAsisLocacionId(p.getAsisLocacionId());
                Asistencia.setAsisIdNumero(p.getAsisIdNumero());
                Asistencia.setAsisCodTrabajo(p.getAsisCodTrabajo());
                Asistencia.setAsisVerificaCod(p.getAsisVerificaCod());
                Asistencia.setAsisNoTarjeta(p.getAsisNoTarjeta());
                Asistencia.setAsisEstadoStr(p.getAsisEstadoStr());

                return new ResponseEntity<>(asistenciaService.save(Asistencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        asistenciaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

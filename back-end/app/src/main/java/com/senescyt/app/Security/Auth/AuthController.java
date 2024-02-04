package com.senescyt.app.Security.Auth;

import com.senescyt.app.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/snc/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/usuarioValido")
    public ResponseEntity<Boolean> usuarioValido(@RequestParam String user) {
        return new ResponseEntity<>(authService.usuarioValido(user), HttpStatus.OK);
    }
}

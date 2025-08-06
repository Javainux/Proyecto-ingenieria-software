package com.MiDoc.Midoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.MiDoc.Midoc.DTO.LoginDTO;
import com.MiDoc.Midoc.DTO.UsuarioPerfilDTO;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cuenta")
@CrossOrigin(
    origins = "*", // Cambia esto por tu dominio en producción
    methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS},
    allowedHeaders = "*"
)
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            Usuario usuario = usuarioRepo.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getCorreo(), loginDTO.getPassword())
            );

            request.getSession(true).setAttribute("usuario", usuario);

            return ResponseEntity.ok(new UsuarioPerfilDTO(usuario));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    // 👇 Manejo explícito del OPTIONS para evitar 502 en Railway
    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build(); // Devuelve 200 OK para preflight
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> perfil(HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario != null) {
            return ResponseEntity.ok(new UsuarioPerfilDTO(usuario));
        } else {
            return ResponseEntity.status(401).body("No autenticado");
        }
    }

    @GetMapping("/test-password")
    public boolean testPassword() {
        String raw = "doctor123";
        String hashed = "$2a$10$M3E9yecu4mJj7cTE.8wEiuWxM6IGbUbCoc3D5CNbTZwOh/c8AlLhK";
        return encoder.matches(raw, hashed);
    }
}

package com.MiDoc.Midoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.MiDoc.Midoc.DTO.LoginDTO;
import com.MiDoc.Midoc.DTO.UsuarioPerfilDTO;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cuenta")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getCorreo(), loginDTO.getPassword())
            );

            // Obtener el correo autenticado
            String correo = auth.getName();

            // Buscar el usuario real en la base de datos
            Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            return ResponseEntity.ok(new UsuarioPerfilDTO(usuario));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
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
    public UsuarioPerfilDTO perfil(@RequestParam String correo) {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UsuarioPerfilDTO(usuario);
    }
}

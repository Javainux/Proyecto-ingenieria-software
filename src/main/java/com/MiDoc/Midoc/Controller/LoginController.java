package com.MiDoc.Midoc.Controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.MiDoc.Midoc.DTO.LoginDTO;
import com.MiDoc.Midoc.DTO.RegistroDTO;
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

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/registro", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptionsRegistro() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registroConFoto(@RequestParam("foto") MultipartFile foto,
                                             @RequestParam("nombre") String nombre,
                                             @RequestParam("correo") String correo,
                                             @RequestParam("password") String password,
                                             @RequestParam("rol") String rol,
                                             @RequestParam("numero") String numero,
                                             @RequestParam("edad") int edad) {
        try {
            // Generar nombre único para la imagen
            String nombreArchivo = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

            // Ruta absoluta en el proyecto
            String carpetaPath = System.getProperty("user.dir") + "/uploads/";
            File carpeta = new File(carpetaPath);
            if (!carpeta.exists()) carpeta.mkdirs();

            // Guardar archivo
            String ruta = carpetaPath + nombreArchivo;
            foto.transferTo(new File(ruta));

            // URL pública para mostrar la imagen
            String urlFoto = "/uploads/" + nombreArchivo;

            // Crear y guardar el usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setContra(encoder.encode(password)); // Encriptado
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setNumero(numero);
            nuevoUsuario.setEdad(edad);
            nuevoUsuario.setFoto_url(urlFoto); // Asegúrate que exista en tu entidad

            usuarioRepo.save(nuevoUsuario);

            // Crear DTO de respuesta
            RegistroDTO respuesta = new RegistroDTO();
            respuesta.setNombre(nombre);
            respuesta.setCorreo(correo);
            respuesta.setPassword(password); // ⚠️ Solo para pruebas
            respuesta.setRol(rol);
            respuesta.setNumero(numero);
            respuesta.setEdad(edad);
            respuesta.setFoto_url(urlFoto);

            return ResponseEntity.ok(respuesta);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al subir la imagen");
        }
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

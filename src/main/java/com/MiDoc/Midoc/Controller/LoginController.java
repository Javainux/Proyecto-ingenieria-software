package com.MiDoc.Midoc.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.MiDoc.Midoc.DTO.LoginDTO;
import com.MiDoc.Midoc.DTO.RegistroDTO;
import com.MiDoc.Midoc.DTO.UsuarioPerfilDTO;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.DoctorRepository;
import com.MiDoc.Midoc.Repository.PacienteRepository;
import com.MiDoc.Midoc.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cuenta")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS}, allowedHeaders = "*")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            Usuario usuario = usuarioRepo.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getCorreo(), loginDTO.getPassword())
            );

            request.getSession(true).setAttribute("usuario", usuario);

            if ("PACIENTE".equals(usuario.getRol())) {
                Paciente paciente = pacienteRepo.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                return ResponseEntity.ok(new UsuarioPerfilDTO(paciente));
            }

            if ("DOCTOR".equals(usuario.getRol())) {
                Doctor doctor = doctorRepo.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
                return ResponseEntity.ok(new UsuarioPerfilDTO(doctor));
            }

            return ResponseEntity.ok(new UsuarioPerfilDTO(usuario));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registroConFoto(@RequestParam("foto") MultipartFile foto,
                                             @RequestParam("nombre") String nombre,
                                             @RequestParam("correo") String correo,
                                             @RequestParam("password") String password,
                                             @RequestParam("rol") String rol,
                                             @RequestParam("numero") String numero,
                                             @RequestParam("edad") int edad,
                                             @RequestParam(value = "curp", required = false) String curp,
                                             @RequestParam(value = "contactoEmergencia", required = false) String contactoEmergencia,
                                             @RequestParam(value = "alergias", required = false) List<String> alergias,
                                             @RequestParam(value = "enfermedadesCronicas", required = false) List<String> enfermedadesCronicas,
                                             @RequestParam(value = "especialidad", required = false) String especialidad,
                                             @RequestParam(value = "cedula", required = false) String cedula,
                                             @RequestParam(value = "descripcion", required = false) String descripcion,
                                             @RequestParam(value = "direccion", required = false) String direccion,
                                             @RequestParam(value = "costoCita", required = false) Double costoCita,
                                             @RequestParam(value = "latitud", required = false) Double latitud,
                                             @RequestParam(value = "longitud", required = false) Double longitud,
                                             @RequestParam(value = "otras_especialidades", required = false) List<String> otrasEspecialidades,
                                             @RequestParam(value = "fechasDisponibles", required = false) List<String> fechasDisponibles) {
        try {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
            String carpetaPath = System.getProperty("user.dir") + "/uploads/";
            File carpeta = new File(carpetaPath);
            if (!carpeta.exists()) carpeta.mkdirs();

            String ruta = carpetaPath + nombreArchivo;
            foto.transferTo(new File(ruta));
            String urlFoto = "/uploads/" + nombreArchivo;

            if ("PACIENTE".equals(rol)) {
                Paciente paciente = new Paciente();
                paciente.setNombre(nombre);
                paciente.setCorreo(correo);
                paciente.setContra(encoder.encode(password));
                paciente.setRol(rol);
                paciente.setNumero(numero);
                paciente.setEdad(edad);
                paciente.setFoto_url(urlFoto);
                paciente.setCurp(curp);
                paciente.setContactoEmergencia(contactoEmergencia);
                paciente.setAlergias(alergias);
                paciente.setEnfermedadesCronicas(enfermedadesCronicas);

                pacienteRepo.save(paciente);
            } else if ("DOCTOR".equals(rol)) {
                Doctor doctor = new Doctor();
                doctor.setNombre(nombre);
                doctor.setCorreo(correo);
                doctor.setContra(encoder.encode(password));
                doctor.setRol(rol);
                doctor.setNumero(numero);
                doctor.setEdad(edad);
                doctor.setFoto_url(urlFoto);
                doctor.setEspecialidad(especialidad);
                doctor.setCedula(cedula);
                doctor.setDescripcion(descripcion);
                doctor.setDireccion(direccion);
                doctor.setCostoCita(costoCita != null ? costoCita : 0.0);
                doctor.setLatitud(latitud);
                doctor.setLongitud(longitud);
                doctor.setOtras_especialidades(otrasEspecialidades);
                doctor.setFechasDisponibles(fechasDisponibles);

                doctorRepo.save(doctor);
            } else {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setCorreo(correo);
                nuevoUsuario.setContra(encoder.encode(password));
                nuevoUsuario.setRol(rol);
                nuevoUsuario.setNumero(numero);
                nuevoUsuario.setEdad(edad);
                nuevoUsuario.setFoto_url(urlFoto);

                usuarioRepo.save(nuevoUsuario);
            }

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
            if ("PACIENTE".equals(usuario.getRol())) {
                Paciente paciente = pacienteRepo.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                return ResponseEntity.ok(new UsuarioPerfilDTO(paciente));
            }
            if ("DOCTOR".equals(usuario.getRol())) {
                Doctor doctor = doctorRepo.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
                return ResponseEntity.ok(new UsuarioPerfilDTO(doctor));
            }
            return ResponseEntity.ok(new UsuarioPerfilDTO(usuario));
        } else {
            return ResponseEntity.status(401).body("No autenticado");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptionsLogin() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/registro", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptionsRegistro() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test-password")
    public boolean testPassword() {
        String raw = "doctor123";
        String hashed = "$2a$10$M3E9yecu4mJj7cTE.8wEiuWxM6IGbUbCoc3D5CNbTZwOh/c8AlLhK";
        return encoder.matches(raw, hashed);
    }
}

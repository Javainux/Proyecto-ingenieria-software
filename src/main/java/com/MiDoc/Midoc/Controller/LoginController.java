package com.MiDoc.Midoc.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.MiDoc.Midoc.DTO.LoginDTO;
import com.MiDoc.Midoc.DTO.RegistroDTO;
import com.MiDoc.Midoc.DTO.UsuarioPerfilDTO;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.DoctorRepository;
import com.MiDoc.Midoc.Repository.PacienteRepository;
import com.MiDoc.Midoc.Repository.UsuarioRepository;
import com.MiDoc.Midoc.Service.UsuarioService;
import com.MiDoc.Midoc.Util.JwtFilter;
import com.MiDoc.Midoc.Util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private JwtUtil jwtUtil = new JwtUtil();

    @Operation(summary = "Login con JSON", description = "Autentica al usuario y devuelve su perfil + token JWT")
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
    try {
        Usuario usuario = usuarioRepo.findByCorreo(loginDTO.getCorreo())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getCorreo(), loginDTO.getPassword())
        );

        List<String> roles = List.of("ROLE_" + usuario.getRol());
        String token = jwtUtil.generateToken(usuario.getCorreo(), roles);

        UsuarioPerfilDTO perfilDTO;

        if ("PACIENTE".equals(usuario.getRol())) {
            Paciente paciente = pacienteRepo.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            perfilDTO = new UsuarioPerfilDTO(paciente);
        } else if ("DOCTOR".equals(usuario.getRol())) {
            Doctor doctor = doctorRepo.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
            perfilDTO = new UsuarioPerfilDTO(doctor);
        } else {
            perfilDTO = new UsuarioPerfilDTO(usuario);
        }

        return ResponseEntity.ok(
            Map.of(
                "token", token,
                "perfil", perfilDTO
            )
        );

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
public ResponseEntity<?> registroConFoto(
        @RequestParam("foto") MultipartFile foto,
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
        @RequestParam(value = "otras_especialidades", required = false) List<String> otrasEspecialidades,
        @RequestParam(value = "fechasDisponibles", required = false) List<String> fechasDisponibles) {

    try {
        if (usuarioRepo.findByCorreo(correo).isPresent()) {
            return ResponseEntity.status(409).body("Ya existe un usuario con ese correo");
        }

        String nombreArchivo = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename().replaceAll("\\s+", "_");
        String carpetaPath = System.getProperty("user.dir") + "/uploads/";
        File carpeta = new File(carpetaPath);
        if (!carpeta.exists()) carpeta.mkdirs();

        String ruta = carpetaPath + nombreArchivo;
        foto.transferTo(new File(ruta));
        String urlFoto = "/uploads/" + nombreArchivo;

        if ("PACIENTE".equalsIgnoreCase(rol)) {
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
            paciente.setAlergias(alergias != null ? alergias : List.of());
            paciente.setEnfermedadesCronicas(enfermedadesCronicas != null ? enfermedadesCronicas : List.of());

            pacienteRepo.save(paciente);
        } else if ("DOCTOR".equalsIgnoreCase(rol)) {
            if (especialidad == null || especialidad.isBlank() || cedula == null || cedula.isBlank()) {
                return ResponseEntity.badRequest().body("Especialidad y cédula son obligatorias para doctores");
            }

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
            doctor.setOtras_especialidades(otrasEspecialidades != null ? otrasEspecialidades : List.of());
            doctor.setFechasDisponibles(fechasDisponibles != null ? fechasDisponibles : List.of());

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
        respuesta.setRol(rol);
        respuesta.setNumero(numero);
        respuesta.setEdad(edad);
        respuesta.setFoto_url(urlFoto);

        return ResponseEntity.ok(respuesta);

    } catch (IOException e) {
        
        return ResponseEntity.status(500).body("Error al subir la imagen");
    }
}

    @PostMapping("/registro/json")
public ResponseEntity<?> registroSinFoto(@RequestBody RegistroDTO dto) {
    try {
        if (usuarioRepo.findByCorreo(dto.getCorreo()).isPresent()) {
            return ResponseEntity.status(409).body("Ya existe un usuario con ese correo");
        }

        String urlFoto = "/uploads/default.png"; // Puedes cambiar esto por una imagen genérica

        if ("PACIENTE".equalsIgnoreCase(dto.getRol())) {
            Paciente paciente = new Paciente();
            paciente.setNombre(dto.getNombre());
            paciente.setCorreo(dto.getCorreo());
            paciente.setContra(encoder.encode(dto.getPassword()));
            paciente.setRol(dto.getRol());
            paciente.setNumero(dto.getNumero());
            paciente.setEdad(dto.getEdad());
            paciente.setFoto_url(urlFoto);
            paciente.setCurp(dto.getCurp());
            paciente.setContactoEmergencia(dto.getContactoEmergencia());
            paciente.setAlergias(dto.getAlergias() != null ? dto.getAlergias() : List.of());
            paciente.setEnfermedadesCronicas(dto.getEnfermedadesCronicas() != null ? dto.getEnfermedadesCronicas() : List.of());

            pacienteRepo.save(paciente);
        } else if ("DOCTOR".equalsIgnoreCase(dto.getRol())) {
            if (dto.getEspecialidad() == null || dto.getEspecialidad().isBlank() ||
                dto.getCedula() == null || dto.getCedula().isBlank()) {
                return ResponseEntity.badRequest().body("Especialidad y cédula son obligatorias para doctores");
            }

            Doctor doctor = new Doctor();
            doctor.setNombre(dto.getNombre());
            doctor.setCorreo(dto.getCorreo());
            doctor.setContra(encoder.encode(dto.getPassword()));
            doctor.setRol(dto.getRol());
            doctor.setNumero(dto.getNumero());
            doctor.setEdad(dto.getEdad());
            doctor.setFoto_url(urlFoto);
            doctor.setEspecialidad(dto.getEspecialidad());
            doctor.setCedula(dto.getCedula());
            doctor.setDescripcion(dto.getDescripcion());
            doctor.setDireccion(dto.getDireccion());
            doctor.setCostoCita(dto.getCostoCita() != null ? dto.getCostoCita() : 0.0);
            doctor.setOtras_especialidades(dto.getOtrasEspecialidades() != null ? dto.getOtrasEspecialidades() : List.of());
            doctor.setFechasDisponibles(dto.getFechasDisponibles() != null ? dto.getFechasDisponibles() : List.of());

            doctorRepo.save(doctor);
        } else {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(dto.getNombre());
            nuevoUsuario.setCorreo(dto.getCorreo());
            nuevoUsuario.setContra(encoder.encode(dto.getPassword()));
            nuevoUsuario.setRol(dto.getRol());
            nuevoUsuario.setNumero(dto.getNumero());
            nuevoUsuario.setEdad(dto.getEdad());
            nuevoUsuario.setFoto_url(urlFoto);

            usuarioRepo.save(nuevoUsuario);
        }

        dto.setFoto_url(urlFoto); // Agregamos la URL por consistencia
        return ResponseEntity.ok(dto);

    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error al registrar usuario");
    }
}


@Autowired
private UsuarioService usuarioService;

@GetMapping("/perfil")
public ResponseEntity<?> perfil(Authentication authentication) {
    System.out.println("🛬 Entrando a /cuenta/perfil con auth: " + authentication);

    try {
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("⚠️ Usuario no autenticado");
            return ResponseEntity.status(401).body("No autenticado");
        }

        String correo = ((UserDetails) authentication.getPrincipal()).getUsername();
        System.out.println("📧 Correo autenticado: " + correo);

        Usuario usuario = usuarioRepo.findByCorreo(correo).orElse(null);
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado en BD para el correo: " + correo);
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        System.out.println("✅ Usuario encontrado: ID=" + usuario.getId() + ", Rol=" + usuario.getRol());
        System.out.println("🧬 Tipo de clase real: " + usuario.getClass().getSimpleName());

        UsuarioPerfilDTO perfilDTO;

        if (usuario instanceof Paciente paciente) {
            perfilDTO = new UsuarioPerfilDTO(paciente);
        } else if (usuario instanceof Doctor doctor) {
            perfilDTO = new UsuarioPerfilDTO(doctor);
        } else {
            perfilDTO = new UsuarioPerfilDTO(usuario);
        }

        return ResponseEntity.ok(perfilDTO);

    } catch (Exception e) {
        System.out.println("🔥 Excepción atrapada en /cuenta/perfil:");
        e.printStackTrace();
        return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
    }
}




    @GetMapping("/debug-auth")
public ResponseEntity<?> debugAuth(Authentication authentication) {
    if (authentication == null) return ResponseEntity.status(401).body("No autenticado");

    return ResponseEntity.ok(
        Map.of(
            "name", authentication.getName(),
            "authorities", authentication.getAuthorities(),
            "principal", authentication.getPrincipal()
        )
    );
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

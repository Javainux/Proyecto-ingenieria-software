package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Mappers.UsuarioMapper;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 👈 Importa esto
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder; // 👈 Declara esto

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) { // 👈 Inyéctalo aquí
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }

   public UsuarioDTO createUsuario(UsuarioDTO dto) {
    Usuario usuario;

    switch (dto.getRol().toLowerCase()) {
        case "doctor":
            usuario = new Doctor(); // o usar un DoctorMapper si tienes
            break;
        case "paciente":
            usuario = new Paciente();
            break;
        default:
            throw new IllegalArgumentException("Rol no reconocido: " + dto.getRol());
    }

    // Asignar campos comunes
    usuario.setNombre(dto.getNombre());
    usuario.setCorreo(dto.getCorreo());
    usuario.setEdad(dto.getEdad());
    usuario.setNumero(dto.getNumero());
    usuario.setFoto_url(dto.getFoto_url());
    usuario.setRol(dto.getRol());

    // 🔐 Encriptar contraseña
    if (dto.getContra() == null || dto.getContra().isBlank()) {
        throw new IllegalArgumentException("La contraseña no puede estar vacía");
    }
    usuario.setContra(passwordEncoder.encode(dto.getContra()));

    Usuario saved = usuarioRepository.save(usuario);
    return usuarioMapper.toDTO(saved);
}


    public UsuarioDTO updateUsuario(Long id, UsuarioDTO dto) {
        Usuario existing = usuarioRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNombre(dto.getNombre());
        existing.setEdad(dto.getEdad());
        existing.setCorreo(dto.getCorreo());

        // 🔐 También puedes cifrar aquí si se actualiza la contraseña
        existing.setContra(passwordEncoder.encode(dto.getContra()));

        existing.setRol(dto.getRol());
        existing.setNumero(dto.getNumero());
        existing.setFoto_url(dto.getFoto_url());

        Usuario updated = usuarioRepository.save(existing);
        return usuarioMapper.toDTO(updated);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }

}

package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.Exception.UsuarioInvalidDataException;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.UsuarioRepository;
import com.MiDoc.Midoc.Util.ValidarUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuario.getEdad() <= 0) {
            throw new UsuarioInvalidDataException("La edad debe ser mayor a 0.");
        } else if (usuario.getEdad() > 120) {
            throw new UsuarioInvalidDataException("La edad no debe ser mayor a 120.");
        }

        if (usuarioRepo.findByCorreo(usuario.getCorreo()).isPresent()) {
        throw new UsuarioInvalidDataException("El correo ya está registrado.");
}

        ValidarUsuario.Validar(usuario);
        usuario.setContra(passwordEncoder.encode(usuario.getContra()));
        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> obtenerUsuario(Long id) {
        return usuarioRepo.findById(id);
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Usuario> actualizarUsuario(Long id, Usuario datosActualizados) {
        ValidarUsuario.Validar(datosActualizados);
        return usuarioRepo.findById(id).map(usuario -> {
            usuario.setNombre(datosActualizados.getNombre());
            usuario.setEdad(datosActualizados.getEdad());
            usuario.setCorreo(datosActualizados.getCorreo());
            usuario.setContra(datosActualizados.getContra());
            usuario.setRol(datosActualizados.getRol());
            return usuarioRepo.save(usuario);
        });
    }
}

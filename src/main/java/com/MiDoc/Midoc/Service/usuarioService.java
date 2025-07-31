package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.repositoryUsuario;
import com.MiDoc.Midoc.Util.ValidarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {
    
    @Autowired
    private repositoryUsuario usuarioRepo;


    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        ValidarUsuario.Validar(usuario);
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

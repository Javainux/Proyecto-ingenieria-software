package com.MiDoc.Midoc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("🔍 Autenticando usuario: " + correo);

        Usuario usuario = usuarioRepo.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rol = usuario.getRol();
        if (rol == null || rol.isBlank()) {
            System.out.println("⚠️ Rol vacío o nulo para usuario: " + correo);
            throw new UsernameNotFoundException("Usuario sin rol asignado");
        }

        String authority = rol.startsWith("ROLE_") ? rol : "ROLE_" + rol.toUpperCase();
        System.out.println("🛡️ Autoridad asignada: " + authority);
        System.out.println("🔑 Contraseña en BD: " + usuario.getContra());

        return new User(
            usuario.getCorreo(), // 👈 Esto será usado como authentication.getName()
            usuario.getContra(),
            List.of(new SimpleGrantedAuthority(authority))
        );
    }
}

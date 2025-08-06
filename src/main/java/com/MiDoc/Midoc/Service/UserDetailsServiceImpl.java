package com.MiDoc.Midoc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    System.out.println("🛡️ Rol asignado: " + usuario.getRol());
    System.out.println("🔑 Contraseña en BD: " + usuario.getContra());

    return new User(
        usuario.getCorreo(),
        usuario.getContra(),
        List.of(new SimpleGrantedAuthority(
            usuario.getRol().startsWith("ROLE_") ? usuario.getRol() : "ROLE_" + usuario.getRol()))
    );
}

}

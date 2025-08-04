package com.MiDoc.Midoc.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.MiDoc.Midoc.Repository.UsuarioRepository;
import com.MiDoc.Midoc.Model.Usuario;

@Component // 👈 asegura que Spring la detecte
public class UsuarioBootstrap {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void crearUsuarioTest() {
        if (usuarioRepo.findByCorreo("doctorcito@example.com").isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setCorreo("doctorcito@example.com");
            usuario.setContra(passwordEncoder.encode("doctor123"));
            usuario.setRol("DOCTOR");
            usuario.setEdad(35);
            usuario.setNombre("Doctorcito");
            usuario.setNumero("2281234567");

            usuarioRepo.save(usuario);
            System.out.println("✅ Usuario de prueba insertado con éxito");
        } else {
            System.out.println("⚠️ El usuario doctorcito@example.com ya existe");
        }
    }
}
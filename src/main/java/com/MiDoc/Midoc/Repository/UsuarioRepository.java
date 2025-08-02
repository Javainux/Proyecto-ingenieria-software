package com.MiDoc.Midoc.Repository;

import com.MiDoc.Midoc.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Ejemplo futuro:
    // Usuario findByCorreo(String correo);
}

package com.MiDoc.Midoc.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MiDoc.Midoc.Model.Cuenta;

public interface CuentaRepository extends JpaRepository <Cuenta, Long>{
Optional<Cuenta> findByUsuario(String usuario);
    
} 
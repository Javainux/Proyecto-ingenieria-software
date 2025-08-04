package com.MiDoc.Midoc.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


import com.MiDoc.Midoc.Model.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
    List<MetodoPago> findByUsuarioId(Long usuarioId); // <-- Esto está bien
}
    

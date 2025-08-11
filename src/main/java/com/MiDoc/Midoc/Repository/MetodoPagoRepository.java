package com.MiDoc.Midoc.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MiDoc.Midoc.Model.MetodoPago;
import com.MiDoc.Midoc.Model.TipoTarjeta;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    // Obtener todos los métodos de pago de un usuario
    List<MetodoPago> findByUsuarioId(Long usuarioId);

    // Buscar por tipo de tarjeta (opcional)
    List<MetodoPago> findByTipo(TipoTarjeta tipo);

    // Buscar por número encriptado (útil si necesitas validar duplicados)
    MetodoPago findByNumeroEncriptado(String numeroEncriptado);
}

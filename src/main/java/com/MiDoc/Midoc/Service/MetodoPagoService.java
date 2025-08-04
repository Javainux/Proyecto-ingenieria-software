package com.MiDoc.Midoc.Service;

import java.util.List;

import com.MiDoc.Midoc.DTO.MetodoPagoDTO;

public interface MetodoPagoService {
    
    MetodoPagoDTO crear(MetodoPagoDTO dto);
    MetodoPagoDTO obtenerPorId(Long id);
    List<MetodoPagoDTO> obtenerTodos();
    MetodoPagoDTO actualizar(Long id, MetodoPagoDTO dto);
    void eliminar(Long id);
}

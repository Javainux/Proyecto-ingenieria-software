package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.MetodoPagoDTO;
import com.MiDoc.Midoc.Model.MetodoPago;
import com.MiDoc.Midoc.Model.Usuario;

public class MetodoPagoMapper {

    public static MetodoPago toEntity(MetodoPagoDTO dto, Usuario usuario) {
        MetodoPago metodo = new MetodoPago();
        metodo.setTipo(dto.getTipo());
        metodo.setDetalles(dto.getDetalles());
        metodo.setNumero(dto.getNumero());
        metodo.setUsuario(usuario);
        
        return metodo;
    }

    public static MetodoPagoDTO toDTO(MetodoPago metodo) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setTipo(metodo.getTipo());
        dto.setDetalles(metodo.getDetalles());
        dto.setNumero(metodo.getNumero());
        dto.setNumeroMasked(metodo.getNumeroMasked());
        dto.setUsuarioId(metodo.getUsuario().getId()); 
        return dto;
}

}

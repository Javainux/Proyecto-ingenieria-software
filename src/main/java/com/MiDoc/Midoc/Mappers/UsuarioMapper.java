package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNumero(usuario.getNumero());
        dto.setNombre(usuario.getNombre());
        dto.setEdad(usuario.getEdad());
        dto.setContra(usuario.getContra());
        dto.setRol(usuario.getRol());
        dto.setCorreo(usuario.getCorreo());
        dto.setFoto_url(usuario.getFoto_url());

        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNumero(dto.getNumero());
        usuario.setNombre(dto.getNombre());
        usuario.setEdad(dto.getEdad());
        usuario.setContra(dto.getContra());
        usuario.setRol(dto.getRol());
        usuario.setCorreo(dto.getCorreo());
        usuario.setFoto_url(dto.getFoto_url());

        return usuario;
    }
}

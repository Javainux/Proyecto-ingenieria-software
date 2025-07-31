package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getContra(),
            usuario.getCorreo(),
            usuario.getRol(),
            usuario.getEdad()
        );
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Usuario(
            dto.getId(),
            dto.getNombre(),
            dto.getEdad(),
            dto.getContra(),
            dto.getRol(),
            dto.getCorreo()
        );
    }
    
}

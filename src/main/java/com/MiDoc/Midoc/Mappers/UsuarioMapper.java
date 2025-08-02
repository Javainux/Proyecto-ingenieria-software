package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Model.Usuario;

/**
 * Mapeador entre entidad Usuario y su DTO.
 */
public class UsuarioMapper {

    // Convierte una entidad Usuario en su DTO correspondiente
    public static UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNumero(),
            usuario.getNombre(),
            usuario.getContra(),
            usuario.getCorreo(),
            usuario.getRol(),
            usuario.getEdad()
        );
    }

    // Convierte un UsuarioDTO en su entidad Usuario
    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Usuario(
            dto.getId(),
            dto.getNumero(),
            dto.getNombre(),
            dto.getEdad(),
            dto.getContra(),
            dto.getRol(),
            dto.getCorreo()
        );
    }
}
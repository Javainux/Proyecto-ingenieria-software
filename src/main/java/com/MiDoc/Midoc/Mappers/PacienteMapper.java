package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Model.Paciente;

/**
 * Mapeador entre entidad Paciente y su DTO.
 */
public class PacienteMapper {

    // Convierte una entidad Paciente en su DTO
    public static PacienteDTO toDto(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        return new PacienteDTO(
            paciente.getId(),
            paciente.getNombre(),
            paciente.getCorreo(),
            paciente.getEdad(),
            paciente.getSintomas(),
            paciente.getDireccion(),
            paciente.getNumero(),
            paciente.getRol(),
            paciente.getContra(),
            paciente.getCurp()
        );
    }

    // Convierte un PacienteDTO en su entidad Paciente
    public static Paciente toEntity(PacienteDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Paciente(
            dto.getId(),
            dto.getCurp(),
            dto.getDireccion(),
            dto.getNombre(),
            dto.getCorreo(),
            dto.getRol(),
            dto.getContra(),
            dto.getEdad(),
            dto.getNumero(),
            dto.getSintomas()
        );
    }
}
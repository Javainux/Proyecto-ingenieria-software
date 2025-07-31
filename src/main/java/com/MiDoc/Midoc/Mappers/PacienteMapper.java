package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Model.Paciente;

public class PacienteMapper {
    
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

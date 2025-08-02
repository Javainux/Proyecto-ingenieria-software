package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Model.Doctor;

public class DoctorMapper {

    // Convierte una entidad Doctor en su DTO correspondiente
    public static DoctorDTO tDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return new DoctorDTO(
            doctor.getId(),
            doctor.getNombre(),
            doctor.getEspecialidad(),
            doctor.getCorreo(),
            doctor.getNumero(),
            doctor.getEdad(),
            doctor.getRol(),
            doctor.getContra()
        );
    }

    // Convierte un DoctorDTO en una entidad Doctor
    public static Doctor toEntity(DoctorDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Doctor(
            dto.getId(),
            dto.getNumero(),
            dto.getEspecialidad(),
            dto.getNombre(),
            dto.getCorreo(),
            dto.getRol(),
            dto.getContra(),
            dto.getEdad()
        );
    }
}
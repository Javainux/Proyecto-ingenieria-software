package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Model.Doctor;

public class DoctorMapper {
    public static DoctorDTO tDto(Doctor doctor){
        if(doctor == null){
            return null;
        }

        return new DoctorDTO(
            doctor.getId(),
            doctor.getNombre(),
            doctor.getCorreo(),
            doctor.getEdad(),
            doctor.getEspecialidad(),
            doctor.getRol(),
            doctor.getContra()
        );
    }

    public static Doctor toEntity(DoctorDTO dto){
          if (dto == null) {
            return null;
        }

         return new Doctor(
            dto.getId(),
            dto.getEspecialidad(),
            dto.getNombre(),
            dto.getCorreo(),
            dto.getRol(),
            dto.getContra(),
            dto.getEdad()
        );
    }
    }


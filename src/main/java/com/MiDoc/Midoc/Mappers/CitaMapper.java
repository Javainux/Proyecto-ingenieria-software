package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;

public class CitaMapper {

    public static CitaDTO toDTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setDoctorId(cita.getDoctor().getId());
        dto.setPacienteId(cita.getPaciente().getId());
        dto.setFecha(cita.getFecha());
        dto.setHora(cita.getHora());
        dto.setMotivo(cita.getMotivo());
        dto.setEstado(cita.getEstado());
        return dto;
    }

    public static Cita toEntity(CitaDTO dto, Doctor doctor, Paciente paciente) {
        Cita cita = new Cita();
        cita.setDoctor(doctor);
        cita.setPaciente(paciente);
        cita.setFecha(dto.getFecha());
        cita.setHora(dto.getHora());
        cita.setMotivo(dto.getMotivo());
        cita.setEstado(dto.getEstado());
        return cita;
    }
}

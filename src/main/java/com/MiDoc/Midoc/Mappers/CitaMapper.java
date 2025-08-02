package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;

public class CitaMapper {

    public static Cita toEntity(CitaDTO dto, Doctor doctor, Paciente paciente) {
        Cita cita = new Cita();
        cita.setDoctor(doctor);
        cita.setPaciente(paciente);
        cita.setFecha(dto.getFecha());
        cita.setHora(dto.getHora());
        cita.setMotivo(dto.getMotivo());
        return cita;
    }
}
package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.MetodoPago;
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

    if (cita.getMetodoPago() != null) {
        dto.setMetodoPagoId(cita.getMetodoPago().getId());
        dto.setMetodoPagoTipo(cita.getMetodoPago().getTipo().toString());
        dto.setMetodoPagoNumeroMasked(cita.getMetodoPago().getNumeroMasked());
    }

    return dto;
}


public static Cita toEntity(CitaDTO dto, Doctor doctor, Paciente paciente, MetodoPago metodoPago) {
    Cita cita = new Cita();

    cita.setDoctor(doctor);
    cita.setPaciente(paciente);

    if (dto.getFecha() == null) {
        throw new IllegalArgumentException("Fecha no puede ser nula");
    }
    cita.setFecha(dto.getFecha());

    if (dto.getHora() == null) {
        throw new IllegalArgumentException("Hora no puede ser nula");
    }
    cita.setHora(dto.getHora());

    if (dto.getMotivo() == null || dto.getMotivo().isBlank()) {
        throw new IllegalArgumentException("Motivo no puede estar vacío");
    }
    cita.setMotivo(dto.getMotivo());

    if (dto.getEstado() == null) {
        throw new IllegalArgumentException("Estado no puede ser nulo");
    }
    cita.setEstado(dto.getEstado());

    if (metodoPago == null) {
        throw new IllegalArgumentException("Método de pago no puede ser nulo");
    }
    cita.setMetodoPago(metodoPago);

    return cita;
}

}

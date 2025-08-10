package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    
    

    public DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) return null;

        DoctorDTO dto = new DoctorDTO();

        // Campos heredados
        dto.setNumero(doctor.getNumero());
        dto.setNombre(doctor.getNombre());
        dto.setEdad(doctor.getEdad());
        dto.setContra(doctor.getContra());
        dto.setRol(doctor.getRol());
        dto.setCorreo(doctor.getCorreo());
        dto.setFoto_url(doctor.getFoto_url());

        // Campos específicos
        dto.setEspecialidad(doctor.getEspecialidad());
        dto.setOtras_especialidades(doctor.getOtras_especialidades());
        dto.setCedula(doctor.getCedula());
        dto.setFechasDisponibles(doctor.getFechasDisponibles());
        dto.setCalificacion(doctor.getCalificacion());
        dto.setDescripcion(doctor.getDescripcion());
        dto.setDireccion(doctor.getDireccion());
        dto.setCostoCita(doctor.getCostoCita());

        return dto;
    }

    public Doctor toEntity(DoctorDTO dto) {
        if (dto == null) return null;

        Doctor doctor = new Doctor();

        // Campos heredados (sin ID porque se genera automáticamente)
        doctor.setNumero(dto.getNumero());
        doctor.setNombre(dto.getNombre());
        doctor.setEdad(dto.getEdad());
        doctor.setContra(dto.getContra());
        doctor.setRol(dto.getRol());
        doctor.setCorreo(dto.getCorreo());
        doctor.setFoto_url(dto.getFoto_url());

        // Campos específicos
        doctor.setEspecialidad(dto.getEspecialidad());
        doctor.setOtras_especialidades(dto.getOtras_especialidades());
        doctor.setCedula(dto.getCedula());
        doctor.setFechasDisponibles(dto.getFechasDisponibles());
        doctor.setCalificacion(dto.getCalificacion());
        doctor.setDescripcion(dto.getDescripcion());
        doctor.setDireccion(dto.getDireccion());
        doctor.setCostoCita(dto.getCostoCita());

        return doctor;
    }
}

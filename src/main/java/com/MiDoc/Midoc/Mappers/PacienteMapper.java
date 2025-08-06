package com.MiDoc.Midoc.Mappers;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    private final UsuarioMapper usuarioMapper;

    @Autowired
    public PacienteMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) return null;

        PacienteDTO dto = new PacienteDTO();

        // Campos heredados
        dto.setId(paciente.getId());
        dto.setNumero(paciente.getNumero());
        dto.setNombre(paciente.getNombre());
        dto.setEdad(paciente.getEdad());
        dto.setContra(paciente.getContra());
        dto.setRol(paciente.getRol());
        dto.setCorreo(paciente.getCorreo());
        dto.setFoto_url(paciente.getFoto_url());

        // Campos específicos
        dto.setCurp(paciente.getCurp());
        dto.setAlergias(paciente.getAlergias());
        dto.setEnfermedadesCronicas(paciente.getEnfermedadesCronicas());
        dto.setContactoEmergencia(paciente.getContactoEmergencia());

        return dto;
    }

    public Paciente toEntity(PacienteDTO dto) {
        if (dto == null) return null;

        Paciente paciente = new Paciente();

        // Campos heredados (sin ID porque se genera automáticamente)
        paciente.setNumero(dto.getNumero());
        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setContra(dto.getContra());
        paciente.setRol(dto.getRol());
        paciente.setCorreo(dto.getCorreo());
        paciente.setFoto_url(dto.getFoto_url());

        // Campos específicos
        paciente.setCurp(dto.getCurp());
        paciente.setAlergias(dto.getAlergias());
        paciente.setEnfermedadesCronicas(dto.getEnfermedadesCronicas());
        paciente.setContactoEmergencia(dto.getContactoEmergencia());

        return paciente;
    }
}

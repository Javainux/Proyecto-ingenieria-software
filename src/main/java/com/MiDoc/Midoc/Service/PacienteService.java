package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Mappers.PacienteMapper;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public List<PacienteDTO> getAllPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO getPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDTO)
                .orElse(null); // Puedes lanzar una excepción personalizada si prefieres
    }

    public PacienteDTO createPaciente(PacienteDTO dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        Paciente saved = pacienteRepository.save(paciente);
        return pacienteMapper.toDTO(saved);
    }

    public PacienteDTO updatePaciente(Long id, PacienteDTO dto) {
        Paciente existing = pacienteRepository.findById(id).orElse(null);
        if (existing == null) return null;

        // Actualizar campos
        existing.setNombre(dto.getNombre());
        existing.setEdad(dto.getEdad());
        existing.setCorreo(dto.getCorreo());
        existing.setContra(dto.getContra());
        existing.setRol(dto.getRol());
        existing.setNumero(dto.getNumero());
        existing.setFoto_url(dto.getFoto_url());
        existing.setCurp(dto.getCurp());
        existing.setAlergias(dto.getAlergias());
        existing.setEnfermedadesCronicas(dto.getEnfermedadesCronicas());
        existing.setContactoEmergencia(dto.getContactoEmergencia());

        Paciente updated = pacienteRepository.save(existing);
        return pacienteMapper.toDTO(updated);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}

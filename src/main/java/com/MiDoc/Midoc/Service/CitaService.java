package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Mappers.CitaMapper;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.CitaRepository;
import com.MiDoc.Midoc.Repository.DoctorRepository;
import com.MiDoc.Midoc.Repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public List<CitaDTO> getAllCitas() {
        return citaRepo.findAll()
            .stream()
            .map(CitaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CitaDTO createCita(CitaDTO dto) {
        Doctor doctor = doctorRepo.findById(dto.getDoctorId()).orElseThrow();
        Paciente paciente = pacienteRepo.findById(dto.getPacienteId()).orElseThrow();
        Cita cita = CitaMapper.toEntity(dto, doctor, paciente);
        return CitaMapper.toDTO(citaRepo.save(cita));
    }

    public CitaDTO getCitaById(Long id) {
        return citaRepo.findById(id).map(CitaMapper::toDTO).orElse(null);
    }

    public CitaDTO updateCita(Long id, CitaDTO dto) {
        return citaRepo.findById(id).map(cita -> {
            cita.setFecha(dto.getFecha());
            cita.setHora(dto.getHora());
            cita.setMotivo(dto.getMotivo());
            cita.setEstado(dto.getEstado());
            return CitaMapper.toDTO(citaRepo.save(cita));
        }).orElse(null);
    }

    public void deleteCita(Long id) {
        citaRepo.deleteById(id);
    }
}

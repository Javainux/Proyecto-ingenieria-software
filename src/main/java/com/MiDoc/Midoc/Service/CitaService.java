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
    System.out.println("📥 DTO recibido: " + dto);

    Doctor doctor = doctorRepo.findById(dto.getDoctorId())
        .orElseThrow(() -> new IllegalArgumentException("❌ Doctor no encontrado: " + dto.getDoctorId()));

    Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
        .orElseThrow(() -> new IllegalArgumentException("❌ Paciente no encontrado: " + dto.getPacienteId()));

    System.out.println("✅ Doctor encontrado: " + doctor.getNombre());
    System.out.println("✅ Paciente encontrado: " + paciente.getNombre());
    System.out.println("🕒 Fecha: " + dto.getFecha() + " | Hora: " + dto.getHora());
    System.out.println("📌 Estado: " + dto.getEstado() + " | Motivo: " + dto.getMotivo());

    try {
        Cita cita = CitaMapper.toEntity(dto, doctor, paciente);
        Cita citaGuardada = citaRepo.save(cita);
        System.out.println("✅ Cita guardada con ID: " + citaGuardada.getId());
        return CitaMapper.toDTO(citaGuardada);
    } catch (Exception e) {
        System.err.println("💥 Error al guardar la cita:");
        e.printStackTrace();
        throw new RuntimeException("Error al guardar la cita: " + e.getMessage());
    }
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

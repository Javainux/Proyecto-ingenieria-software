package com.MiDoc.Midoc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Mappers.CitaMapper;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.CitaRepository;
import com.MiDoc.Midoc.Repository.DoctorRepository;
import com.MiDoc.Midoc.Repository.PacienteRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public Cita crearCita(CitaDTO citaDTO) {
        Doctor doctor = doctorRepo.findById(citaDTO.getDoctorId()).orElseThrow();
        Paciente paciente = pacienteRepo.findById(citaDTO.getPacienteId()).orElseThrow();

        boolean conflicto = citaRepository.existsByDoctorIdAndFechaAndHora(
            doctor.getId(), citaDTO.getFecha(), citaDTO.getHora()
        );

        if (conflicto) throw new IllegalStateException("El doctor ya tiene una cita en ese horario.");

        return citaRepository.save(CitaMapper.toEntity(citaDTO, doctor, paciente));
    }
}

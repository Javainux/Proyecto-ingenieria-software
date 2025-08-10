package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Mappers.DoctorMapper;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO)
                .orElse(null);
    }

    public DoctorDTO createDoctor(DoctorDTO dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toDTO(saved);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor existing = doctorRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNombre(dto.getNombre());
        existing.setEdad(dto.getEdad());
        existing.setCorreo(dto.getCorreo());
        existing.setContra(dto.getContra());
        existing.setRol(dto.getRol());
        existing.setNumero(dto.getNumero());
        existing.setFoto_url(dto.getFoto_url());
        existing.setEspecialidad(dto.getEspecialidad());
        existing.setOtras_especialidades(dto.getOtras_especialidades());
        existing.setCedula(dto.getCedula());
        existing.setFechasDisponibles(dto.getFechasDisponibles());
        existing.setCalificacion(dto.getCalificacion());
        existing.setDescripcion(dto.getDescripcion());
        existing.setDireccion(dto.getDireccion());
        existing.setCostoCita(dto.getCostoCita());
    

        Doctor updated = doctorRepository.save(existing);
        return doctorMapper.toDTO(updated);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

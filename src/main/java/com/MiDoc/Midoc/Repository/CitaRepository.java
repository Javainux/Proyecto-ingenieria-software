package com.MiDoc.Midoc.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MiDoc.Midoc.Model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);
    List<Cita> findByDoctorId(Long doctorId);
    boolean existsByDoctorIdAndFechaAndHora(Long doctorId, LocalDate fecha, LocalTime hora);
}


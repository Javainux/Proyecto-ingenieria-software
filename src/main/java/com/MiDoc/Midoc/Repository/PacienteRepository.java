package com.MiDoc.Midoc.Repository;

import com.MiDoc.Midoc.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Ejemplo de método personalizado:
    // List<Paciente> findByApellido(String apellido);
}

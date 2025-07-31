package com.MiDoc.Midoc.Repository;

import com.MiDoc.Midoc.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RepositoryPaciente extends JpaRepository<Paciente, Long>{}

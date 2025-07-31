package com.MiDoc.Midoc.Repository;

import com.MiDoc.Midoc.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDoctor extends JpaRepository <Doctor, Long>{}

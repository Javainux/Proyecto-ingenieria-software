package com.MiDoc.Midoc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MiDoc.Midoc.Model.Usuario;

public interface repositoryUsuario extends JpaRepository <Usuario, Long>{}

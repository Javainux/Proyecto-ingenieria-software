package com.MiDoc.Midoc.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.MiDoc.Midoc.DTO.*;
import com.MiDoc.Midoc.Model.Cuenta;
import com.MiDoc.Midoc.Repository.UsuarioRepository;

@Component
public class CuentaMapper {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Cuenta toEntity(CuentaRequestDTO dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(dto.getUsuario());
        cuenta.setPassword(dto.getPassword());
        cuenta.setRol(dto.getRol());
        cuenta.setUsuarioPerfil(usuarioRepo.findById(dto.getUsuarioPerfilId()).orElse(null));
        return cuenta;
    }

    public CuentaResponseDTO toDTO(Cuenta cuenta) {
        CuentaResponseDTO dto = new CuentaResponseDTO();
        dto.setId(cuenta.getId());
        dto.setUsuario(cuenta.getUsuario());
        dto.setRol(cuenta.getRol());
        return dto;
    }
}

package com.MiDoc.Midoc.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.MiDoc.Midoc.DTO.*;
import com.MiDoc.Midoc.Model.Cuenta;
import com.MiDoc.Midoc.Mappers.CuentaMapper;
import com.MiDoc.Midoc.Repository.CuentaRepository;

@Service
public class CuentaService {
    
    @Autowired
    private CuentaRepository cuentaRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CuentaMapper mapper;

    public CuentaResponseDTO crearCuenta(CuentaRequestDTO dto) {
    Cuenta nueva = mapper.toEntity(dto);
    nueva.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔒 Aquí encriptamos
    return mapper.toDTO(cuentaRepo.save(nueva));
}

    public CuentaResponseDTO obtenerCuenta(Long id) {
        Cuenta cuenta = cuentaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapper.toDTO(cuenta);
    }

    public Optional<Cuenta> buscarPorUsuario(String usuario) {
    return cuentaRepo.findByUsuario(usuario);
}

}

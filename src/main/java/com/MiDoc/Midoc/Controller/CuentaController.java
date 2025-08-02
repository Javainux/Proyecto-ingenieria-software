package com.MiDoc.Midoc.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.MiDoc.Midoc.DTO.*;
import com.MiDoc.Midoc.Mappers.CuentaMapper;
import com.MiDoc.Midoc.Model.Cuenta;
import com.MiDoc.Midoc.Service.CuentaService;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CuentaMapper mapper;


    @PostMapping("/crear")
    public ResponseEntity<CuentaResponseDTO> crearCuenta(@RequestBody CuentaRequestDTO dto) {
        return ResponseEntity.ok(service.crearCuenta(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> obtenerCuenta(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerCuenta(id));
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody CuentaRequestDTO dto) {
    Optional<Cuenta> cuentaOpt = service.buscarPorUsuario(dto.getUsuario());
    if (cuentaOpt.isPresent()) {
        Cuenta cuenta = cuentaOpt.get();
        if (passwordEncoder.matches(dto.getPassword(), cuenta.getPassword())) {
            return ResponseEntity.ok(mapper.toDTO(cuenta));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
}

}

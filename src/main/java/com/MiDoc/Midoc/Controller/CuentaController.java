package com.MiDoc.Midoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.MiDoc.Midoc.DTO.*;
import com.MiDoc.Midoc.Mappers.CuentaMapper;
import com.MiDoc.Midoc.Model.Cuenta;
import com.MiDoc.Midoc.Service.CuentaService;

import java.util.Optional;

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
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Optional<Cuenta> cuentaOpt = service.buscarPorUsuario(dto.getUsuario());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Credenciales inválidas"));
        }

        Cuenta cuenta = cuentaOpt.get();
        if (!passwordEncoder.matches(dto.getPassword(), cuenta.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Credenciales inválidas"));
        }

        CuentaResponseDTO response = mapper.toDTO(cuenta);
        return ResponseEntity.ok(response);
    }

    // Clase interna para respuestas de error
    static class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}

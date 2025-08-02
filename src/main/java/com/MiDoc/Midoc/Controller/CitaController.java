package com.MiDoc.Midoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Model.Cita;
import com.MiDoc.Midoc.Service.CitaService;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CitaDTO citaDTO) {
        try {
            Cita cita = citaService.crearCita(citaDTO);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

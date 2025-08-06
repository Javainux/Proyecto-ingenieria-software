package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.CitaDTO;
import com.MiDoc.Midoc.Service.CitaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Citas", description = "Operaciones CRUD para citas médicas")
@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Listar todas las citas")
    @GetMapping
    public List<CitaDTO> listarCitas() {
        return citaService.getAllCitas();
    }

    @Operation(summary = "Registrar una nueva cita")
    @PostMapping
    public ResponseEntity<CitaDTO> crear(@RequestBody CitaDTO dto) {
        return ResponseEntity.ok(citaService.createCita(dto));
    }

    @Operation(summary = "Obtener una cita por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtener(@PathVariable Long id) {
        CitaDTO dto = citaService.getCitaById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar una cita por ID")
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id, @RequestBody CitaDTO dto) {
        CitaDTO actualizado = citaService.updateCita(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una cita por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}

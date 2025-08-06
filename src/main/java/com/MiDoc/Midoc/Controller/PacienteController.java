package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Service.PacienteService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pacientes", description = "Operaciones CRUD para pacientes")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Listar todos los pacientes")
    @GetMapping
    public List<PacienteDTO> listarPacientes() {
        return pacienteService.getAllPacientes();
    }

    @Operation(summary = "Registrar un nuevo paciente")
    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@RequestBody @Valid PacienteDTO dto) {
        PacienteDTO creado = pacienteService.createPaciente(dto);
        return ResponseEntity.ok(creado);
    }

    @Operation(summary = "Obtener un paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(@PathVariable Long id) {
        PacienteDTO dto = pacienteService.getPacienteById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar los datos de un paciente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long id,
                                                          @RequestBody @Valid PacienteDTO dto) {
        PacienteDTO actualizado = pacienteService.updatePaciente(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un paciente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }
}

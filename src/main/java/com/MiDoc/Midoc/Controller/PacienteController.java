package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Mappers.PacienteMapper;
import com.MiDoc.Midoc.Model.Paciente;
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
        return pacienteService.listarPacientes().stream()
                .map(PacienteMapper::toDto)
                .toList();
    }

    @Operation(summary = "Registrar un nuevo paciente")
    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@RequestBody @Valid PacienteDTO dto){
        Paciente paciente = PacienteMapper.toEntity(dto);
        Paciente creado = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.ok(PacienteMapper.toDto(creado));
    }

    @Operation(summary = "Obtener un paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(@PathVariable Long id){
        Paciente paciente = pacienteService.obtenerPaciente(id);
        PacienteDTO dto = PacienteMapper.toDto(paciente);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Eliminar un paciente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        return pacienteService.eliminarPaciente(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar los datos de un paciente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long id,
                                                          @RequestBody @Valid PacienteDTO dto){
        Paciente actualizado = PacienteMapper.toEntity(dto);
        return pacienteService.actualizarPaciente(id, actualizado)
                .map(PacienteMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

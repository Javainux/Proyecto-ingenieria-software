package com.MiDoc.Midoc.Controller;
import com.MiDoc.Midoc.DTO.PacienteDTO;
import com.MiDoc.Midoc.Mappers.PacienteMapper;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.RepositoryPaciente;
import com.MiDoc.Midoc.Service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired 
    private PacienteService pacienteService;

    @GetMapping
    public List<PacienteDTO> listarPacientes() {
        return pacienteService.listarPacientes().stream()
                              .map(PacienteMapper::toDto)
                              .toList();
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@RequestBody @Valid PacienteDTO dto){
        Paciente paciente = PacienteMapper.toEntity(dto);
        Paciente creado = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.ok(PacienteMapper.toDto(creado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(@PathVariable Long id){
        return pacienteService.obtenerPaciente(id)
                .map(PacienteMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        return pacienteService.eliminarPaciente(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

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

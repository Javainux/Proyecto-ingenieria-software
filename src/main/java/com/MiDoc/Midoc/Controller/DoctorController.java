package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Mappers.DoctorMapper;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Service.DoctorService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Doctores", description = "Operaciones CRUD para doctores")
@RestController
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Listar todos los doctores")
    @GetMapping
    public List<DoctorDTO> listarDoctores() {
        return doctorService.listarDoctores().stream()
                .map(DoctorMapper::tDto)
                .toList();
    }

    @Operation(summary = "Registrar un nuevo doctor")
    @PostMapping
    public ResponseEntity<DoctorDTO> registrarDoctor(@RequestBody @Valid DoctorDTO dto) {
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor savedDoctor = doctorService.registrarDoctor(doctor);
        return ResponseEntity.ok(DoctorMapper.tDto(savedDoctor));
    }

    @Operation(summary = "Obtener un doctor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> obtenerDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.obtenerDoctor(id);
        DoctorDTO dto = DoctorMapper.tDto(doctor);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Eliminar un doctor por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        return doctorService.eliminarDoctor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un doctor por ID")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> actualizarDoctor(
            @PathVariable Long id,
            @RequestBody @Valid DoctorDTO dto) {
        Doctor actualizado = DoctorMapper.toEntity(dto);
        return doctorService.actualizarDoctor(id, actualizado)
                .map(DoctorMapper::tDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

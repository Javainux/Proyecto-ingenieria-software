package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Service.DoctorService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Doctores", description = "Operaciones CRUD para doctores")
@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Listar todos los doctores")
    @GetMapping
public ResponseEntity<?> listarDoctores() {
    System.out.println("📥 Petición recibida en /api/doctores");
    return ResponseEntity.ok(List.of(
        Map.of(
            "nombre", "Dr. Prueba",
            "especialidad", "Cardiología",
            "calificacion", 4.9,
            "direccion", "Calle Falsa 123"
        )
    ));
}


    @Operation(summary = "Registrar un nuevo doctor")
    @PostMapping
    public ResponseEntity<DoctorDTO> registrarDoctor(@RequestBody @Valid DoctorDTO dto) {
        DoctorDTO creado = doctorService.createDoctor(dto);
        return ResponseEntity.ok(creado);
    }

    @Operation(summary = "Obtener un doctor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> obtenerDoctor(@PathVariable Long id) {
        DoctorDTO dto = doctorService.getDoctorById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un doctor por ID")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> actualizarDoctor(@PathVariable Long id,
                                                      @RequestBody @Valid DoctorDTO dto) {
        DoctorDTO actualizado = doctorService.updateDoctor(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un doctor por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar doctores destacados")
@GetMapping("/destacados")
public List<DoctorDTO> listarDestacados() {
    return doctorService.getAllDoctors();
}

}

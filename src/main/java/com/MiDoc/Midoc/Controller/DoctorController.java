package com.MiDoc.Midoc.Controller;
import com.MiDoc.Midoc.DTO.DoctorDTO;
import com.MiDoc.Midoc.Mappers.DoctorMapper;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Repository.RepositoryDoctor;
import com.MiDoc.Midoc.Service.DoctorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> listarDoctores() {
        return doctorService.listarDoctores().stream()
                            .map(DoctorMapper::tDto)
                            .toList();
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> registrarDoctor(@RequestBody @Valid DoctorDTO dto) {
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor savedDoctor = doctorService.registrarDoctor(doctor);
        return ResponseEntity.ok(DoctorMapper.tDto(savedDoctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> obtenerDoctor(@PathVariable Long id) {
        return doctorService.obtenerDoctor(id)
                .map(DoctorMapper::tDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        return doctorService.eliminarDoctor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> actualizarDoctor(@PathVariable Long id,
                                                      @RequestBody @Valid DoctorDTO dto) {
        Doctor actualizado = DoctorMapper.toEntity(dto);
        return doctorService.actualizarDoctor(id, actualizado)
                .map(DoctorMapper::tDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

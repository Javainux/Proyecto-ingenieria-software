package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Service.UsuarioService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid UsuarioDTO dto) {
        if (usuarioService.existsByCorreo(dto.getCorreo())) {
            return ResponseEntity.status(409).body("Correo ya registrado");
        }

        UsuarioDTO creado = usuarioService.createUsuario(dto);
        return ResponseEntity.ok(creado);
    }


    @Operation(summary = "Obtener un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO dto = usuarioService.getUsuarioById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar los datos de un usuario por ID")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id,
                                                        @RequestBody @Valid UsuarioDTO dto) {
        UsuarioDTO actualizado = usuarioService.updateUsuario(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");
    }


}


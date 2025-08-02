package com.MiDoc.Midoc.Controller;

import com.MiDoc.Midoc.DTO.UsuarioDTO;
import com.MiDoc.Midoc.Mappers.UsuarioMapper;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Service.UsuarioService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios base del sistema")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService UsuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return UsuarioService.listarUsuarios().stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody @Valid UsuarioDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario creado = UsuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(UsuarioMapper.toDto(creado));
    }

    @Operation(summary = "Obtener un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        return UsuarioService.obtenerUsuario(id)
                .map(UsuarioMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        return UsuarioService.eliminarUsuario(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un usuario por ID")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id,
                                                        @RequestBody @Valid UsuarioDTO dto) {
        Usuario actualizado = UsuarioMapper.toEntity(dto);
        return UsuarioService.actualizarUsuario(id, actualizado)
                .map(UsuarioMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

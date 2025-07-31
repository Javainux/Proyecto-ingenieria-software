package com.MiDoc.Midoc.Controller; import com.MiDoc.Midoc.DTO.PacienteDTO; import com.MiDoc.

Midoc.DTO.UsuarioDTO; import com.MiDoc.Midoc.Mappers.PacienteMapper; import com.MiDoc.Midoc.

Mappers.UsuarioMapper; import com.MiDoc.Midoc.Model.Doctor; import com.MiDoc.Midoc.Model.Paciente; import com.MiDoc.Midoc.Model.Usuario; import com.MiDoc.Midoc.Repository.
repositoryUsuario; import com.MiDoc.Midoc.Service.usuarioService; import jakarta.validation.

Valid; import java.util.List; import java.util.Optional; import org.springframework.beans.

factory.annotation.Autowired; import org.springframework.data.repository.support.Repositories; 



import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private usuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios().stream()
                             .map(UsuarioMapper::toDto)
                             .toList();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody @Valid UsuarioDTO dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario creado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(UsuarioMapper.toDto(creado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id){
        return usuarioService.obtenerUsuario(id)
                .map(UsuarioMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        return usuarioService.eliminarUsuario(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        Usuario actualizado = UsuarioMapper.toEntity(dto);
        return usuarioService.actualizarUsuario(id, actualizado)
                .map(UsuarioMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

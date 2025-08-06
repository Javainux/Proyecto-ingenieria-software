package com.MiDoc.Midoc.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MiDoc.Midoc.DTO.MetodoPagoDTO;
import com.MiDoc.Midoc.Model.MetodoPago;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.MetodoPagoRepository;
import com.MiDoc.Midoc.Repository.UsuarioRepository;
import com.MiDoc.Midoc.Service.MetodoPagoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/metodos-pago")

public class MetodoPagoController {

    @Autowired
    private  MetodoPagoService metodoPagoService;



    @PostMapping
    public ResponseEntity<MetodoPagoDTO> crearMetodo(@RequestBody @Valid MetodoPagoDTO dto) {
        MetodoPagoDTO creado = metodoPagoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> obtenerPorId(@PathVariable Long id) {
        MetodoPagoDTO encontrado = metodoPagoService.obtenerPorId(id);
        return ResponseEntity.ok(encontrado);
    }

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> obtenerTodos() {
        List<MetodoPagoDTO> lista = metodoPagoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizar(@PathVariable Long id, @RequestBody @Valid MetodoPagoDTO dto) {
        MetodoPagoDTO actualizado = metodoPagoService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        metodoPagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package com.MiDoc.Midoc.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.MiDoc.Midoc.DTO.MetodoPagoDTO;
import com.MiDoc.Midoc.Mappers.MetodoPagoMapper;
import com.MiDoc.Midoc.Model.MetodoPago;
import com.MiDoc.Midoc.Model.Usuario;
import com.MiDoc.Midoc.Repository.MetodoPagoRepository;
import com.MiDoc.Midoc.Repository.UsuarioRepository;



@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public MetodoPagoDTO crear(MetodoPagoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        MetodoPago metodo = MetodoPagoMapper.toEntity(dto, usuario);
        MetodoPago guardado = metodoPagoRepository.save(metodo);
        return MetodoPagoMapper.toDTO(guardado);
    }

    @Override
    public MetodoPagoDTO obtenerPorId(Long id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        return MetodoPagoMapper.toDTO(metodo);
    }

    @Override
    public List<MetodoPagoDTO> obtenerTodos() {
        return metodoPagoRepository.findAll().stream()
            .map(MetodoPagoMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoDTO actualizar(Long id, MetodoPagoDTO dto) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        
        metodo.setTipo(dto.getTipo());
        metodo.setDetalles(dto.getDetalles());
        metodo.setNumero(dto.getNumero());
        // No cambiamos usuario aquí por seguridad
        
        MetodoPago actualizado = metodoPagoRepository.save(metodo);
        return MetodoPagoMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        metodoPagoRepository.delete(metodo);
    }
}

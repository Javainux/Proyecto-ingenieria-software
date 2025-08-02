package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.Exception.PacienteInvalidDataException;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.PacienteRepository;
import com.MiDoc.Midoc.Util.ValidarPaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepo;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepo) {
        this.pacienteRepo = pacienteRepo;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepo.findAll();
    }

    public Paciente registrarPaciente(Paciente paciente) {
        if (paciente.getEdad() <= 0) {
            throw new PacienteInvalidDataException("La edad debe ser mayor a 0.");
        } else if (paciente.getEdad() > 120) {
            throw new PacienteInvalidDataException("La edad no debe ser mayor a 120.");
        }

        ValidarPaciente.Validar(paciente);
        return pacienteRepo.save(paciente);
    }

    public Paciente obtenerPaciente(Long id) {
        return pacienteRepo.findById(id)
                .orElseThrow(() -> new PacienteInvalidDataException("El id " + id + " no existe."));
    }

    public boolean eliminarPaciente(Long id) {
        if (pacienteRepo.existsById(id)) {
            pacienteRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Paciente> actualizarPaciente(Long id, Paciente datosActualizados) {
        ValidarPaciente.Validar(datosActualizados);
        return pacienteRepo.findById(id).map(paciente -> {
            paciente.setNombre(datosActualizados.getNombre());
            paciente.setEdad(datosActualizados.getEdad());
            paciente.setCorreo(datosActualizados.getCorreo());
            paciente.setContra(datosActualizados.getContra());
            paciente.setCurp(datosActualizados.getCurp());
            paciente.setDireccion(datosActualizados.getDireccion());
            paciente.setRol(datosActualizados.getRol());
            return pacienteRepo.save(paciente);
        });
    }
}

package com.MiDoc.Midoc.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.MiDoc.Midoc.Util.ValidarPaciente;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Repository.RepositoryPaciente;

@Service
public class PacienteService {

    private RepositoryPaciente pacienteRepo; 

    

    public List<Paciente> listarPacientes(){
        return pacienteRepo.findAll();
    }

    public Paciente registrarPaciente(Paciente paciente){
        ValidarPaciente.Validar(paciente);
        return pacienteRepo.save(paciente);
    }

    public Optional<Paciente> obtenerPaciente(Long id){
        return pacienteRepo.findById(id);
    }

    public boolean eliminarPaciente(Long id){
        if(pacienteRepo.existsById(id)){
            pacienteRepo.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional <Paciente> actualizarPaciente(Long id, Paciente datosActualizados) {
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

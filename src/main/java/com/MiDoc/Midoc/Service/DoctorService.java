package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.Controller.DoctorController;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Util.ValidarDoctor;

import com.MiDoc.Midoc.Repository.RepositoryDoctor;
import com.MiDoc.Midoc.Repository.repositoryUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private RepositoryDoctor doctorRepo;

    public List <Doctor> listarDoctores(){
        return doctorRepo.findAll();
    }

    
    public Doctor registrarDoctor(Doctor doctor){
        ValidarDoctor.Validar(doctor);
        return doctorRepo.save(doctor);
    }

    public Optional <Doctor> obtenerDoctor(Long id){
        return doctorRepo.findById(id);
    }

    public boolean eliminarDoctor(Long id){
        if(doctorRepo.existsById(id)){
            doctorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Doctor> actualizarDoctor(Long id, Doctor datosActualizados) {
        ValidarDoctor.Validar(datosActualizados);
        return doctorRepo.findById(id).map(doctor -> {
            doctor.setNombre(datosActualizados.getNombre());
            doctor.setEspecialidad(datosActualizados.getEspecialidad());
            doctor.setCorreo(datosActualizados.getCorreo());
            doctor.setContra(datosActualizados.getContra());
            doctor.setRol(datosActualizados.getRol());
            return doctorRepo.save(doctor);
        });
    }
}

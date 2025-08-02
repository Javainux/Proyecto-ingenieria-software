package com.MiDoc.Midoc.Service;

import com.MiDoc.Midoc.Exception.DoctorInvalidDataException;
import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Util.ValidarDoctor;
import com.MiDoc.Midoc.Repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;

    @Autowired
    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    // ... tus métodos van aquí



    public List <Doctor> listarDoctores(){
        return doctorRepo.findAll();
    }

    
    public Doctor registrarDoctor(Doctor doctor){

        if(doctor.getEdad() <=0){
            throw new DoctorInvalidDataException("La edad debe de ser mayo a 0");
        }else if(doctor.getEdad() > 120){
            throw new DoctorInvalidDataException("La edad no debe de ser mayor de 120");
        }
        ValidarDoctor.Validar(doctor);
        return doctorRepo.save(doctor);
    }

    public Doctor obtenerDoctor(Long id){
        return doctorRepo.findById(id).orElseThrow(()
            -> new DoctorInvalidDataException("El id " + id + " no existe.")
        );
    }

    public boolean eliminarDoctor(Long id){
        if(doctorRepo.existsById(id)){
            doctorRepo.deleteById(id) ;
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

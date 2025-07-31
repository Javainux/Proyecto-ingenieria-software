package com.MiDoc.Midoc.Util;

import com.MiDoc.Midoc.Model.Doctor;

public class ValidarDoctor {

    public static void Validar(Doctor doctor){
        if(doctor.getNombre() == null || doctor.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre del doctor es obligatorio.");
        }
         if(doctor.getCorreo() == null || doctor.getCorreo().isBlank()){
            throw new IllegalArgumentException("El correo del doctor es obligatorio.");
        }
        else if(!doctor.getCorreo().contains("@")){
            throw new IllegalArgumentException("Correo no valido");
        }
        if(doctor.getEspecialidad() == null || doctor.getEspecialidad().isBlank()){
            throw new IllegalArgumentException("La especialidad es obligatoria ");
        }
    }
    
}

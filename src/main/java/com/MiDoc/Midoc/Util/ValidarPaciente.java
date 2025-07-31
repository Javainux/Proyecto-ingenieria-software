package com.MiDoc.Midoc.Util;
import com.MiDoc.Midoc.Model.Paciente;
public class ValidarPaciente {

    public static void Validar(Paciente paciente){
        if(paciente.getNombre() == null || paciente.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre del paciente es obligatorio.");
        }
        if (paciente.getEdad() < 0 || paciente.getEdad() > 110) {
        throw new IllegalArgumentException("Edad inválida");
    }
    }
    
}

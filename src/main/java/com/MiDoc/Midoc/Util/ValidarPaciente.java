package com.MiDoc.Midoc.Util;

import com.MiDoc.Midoc.Model.Paciente;

public class ValidarPaciente {

    public static void Validar(Paciente paciente) {

        if (esInvalido(paciente.getNombre())) {
            throw new IllegalArgumentException("El nombre del paciente es obligatorio.");
        }

        if (!edadValida(paciente.getEdad())) {
            throw new IllegalArgumentException("La edad del paciente debe estar entre 1 y 110 años.");
        }

        
    }

    private static boolean esInvalido(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private static boolean edadValida(int edad) {
        return edad > 0 && edad <= 110;
    }
}

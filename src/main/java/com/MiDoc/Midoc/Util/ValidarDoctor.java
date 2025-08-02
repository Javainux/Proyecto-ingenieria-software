package com.MiDoc.Midoc.Util;

import com.MiDoc.Midoc.Model.Doctor;

public class ValidarDoctor {

    public static void Validar(Doctor doctor) {

        // Nombre
        if (esInvalido(doctor.getNombre())) {
            throw new IllegalArgumentException("El nombre del doctor es obligatorio.");
        }

        // Correo
        if (esInvalido(doctor.getCorreo())) {
            throw new IllegalArgumentException("El correo del doctor es obligatorio.");
        } else if (!correoValido(doctor.getCorreo())) {
            throw new IllegalArgumentException("El correo proporcionado no es válido.");
        }

        // Especialidad
        if (esInvalido(doctor.getEspecialidad())) {
            throw new IllegalArgumentException("La especialidad del doctor es obligatoria.");
        }
    }

    private static boolean esInvalido(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private static boolean correoValido(String correo) {
        return correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}

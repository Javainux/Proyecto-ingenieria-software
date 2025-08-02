package com.MiDoc.Midoc.Util;

import com.MiDoc.Midoc.Model.Usuario;

public class ValidarUsuario {

    public static void Validar(Usuario usuario) {

        if (esInvalido(usuario.getNombre())) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio.");
        }

        if (esInvalido(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo del usuario es obligatorio.");
        } else if (!correoValido(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo del usuario no es válido.");
        }
    }

    private static boolean esInvalido(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private static boolean correoValido(String correo) {
        return correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}

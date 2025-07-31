package com.MiDoc.Midoc.Util;
import com.MiDoc.Midoc.Model.Usuario;

public class ValidarUsuario {

    public static void Validar(Usuario usuario){
        if(usuario.getNombre() == null ||usuario.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre del usuario es obligaotrio.");
        }
        if(usuario.getCorreo() == null ||!usuario.getCorreo().contains("@")){
            throw new IllegalArgumentException("Correo invalido");
        }
    }
    
}

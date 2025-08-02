package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    private Long id;

    @Max(value = 120, message = "La edad no puede pasar de 120.")
    @Min(value = 18, message = "La edad minima es de 18")
    private int edad;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe de tener al menos 6 caracteres.")
    private String contra;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String correo;

    @NotBlank(message = "El rol es obligatorio")
    private String rol; 

    public UsuarioDTO(){}

     public UsuarioDTO(Long id, String nombre, String contra, String correo, String rol, int edad){
        this.id = id;
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.rol = rol;
        this.edad = edad;
    }

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getContra(){
        return contra;
    }

    public void setContra(String contra){
        this.contra = contra;
    }


    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }
}

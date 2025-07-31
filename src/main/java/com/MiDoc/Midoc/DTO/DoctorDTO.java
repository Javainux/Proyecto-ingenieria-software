package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DoctorDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Correo Invalido.")
    private String correo;

    @Min(value = 18, message = "La edad minima es de 18.")
    private int edad;

    @NotBlank(message = "La especialidad es obligatoria.")
    private String especialidad;

    private String rol;
    private String contra;

    public DoctorDTO(){}

    public DoctorDTO(Long id, String nombre, String correo, int edad, String especialidad, String rol, String contra){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.especialidad = especialidad;
        this.rol = rol;
        this.contra = contra;
    }

    public Long getId(){
        return id;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreo(){
        return correo;
    } 

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}

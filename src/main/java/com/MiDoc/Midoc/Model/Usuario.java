package com.MiDoc.Midoc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.InheritanceType;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    private String nombre;
    private int edad;
    private String contra;
    private String rol;
    private String correo;

    public Usuario(){}

    public Usuario(Long id, String nombre, int edad, String contra, String rol, String correo){
        this.nombre = nombre;
        this.edad = edad;
        this.contra = contra;
        this.rol = rol;
        this.correo = correo;
        this.id = id;
    }

    public Long getId(){
        return id;
    }


    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this. nombre = nombre;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getContra(){
        return contra;
    }

    public void setContra(String contra){
        this.contra = contra;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }



    
}

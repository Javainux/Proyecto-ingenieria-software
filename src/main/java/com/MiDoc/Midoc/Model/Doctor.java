package com.MiDoc.Midoc.Model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Usuario{



    private String especialidad;

    public Doctor(){
        super();
    }

   public Doctor(Long id, String especialidad, String nombre, String correo, String rol, String contra, int edad) {
    super(id, nombre, edad, contra, rol, correo); // ← ahora cada parámetro encaja con Usuario
    this.especialidad = especialidad;
}


    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    
    
}

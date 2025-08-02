package com.MiDoc.Midoc.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Schema(description = "Entidad que representa a un doctor, hereda de Usuario")
public class Doctor extends Usuario {

    @Schema(description = "Especialidad médica del doctor", example = "Cardiología")
    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

  
    public Doctor() {
        super();
    }

  
    public Doctor(Long id, String numero, String especialidad, String nombre, String correo, String rol, String contra, Integer edad) {
        super(id, numero, nombre, edad, contra, rol, correo); // Hereda campos de Usuario
        this.especialidad = especialidad;
    }


    public String getEspecialidad() {
        return especialidad;
    }

  
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

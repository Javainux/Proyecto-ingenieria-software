package com.MiDoc.Midoc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Schema(description = "Entidad que representa a un paciente, hereda de Usuario")
public class Paciente extends Usuario {

    @Schema(description = "CURP del paciente", example = "GOPL920305HMCLNS08")
    @NotBlank(message = "El CURP no puede estar vacío")
    private String curp;

    @Schema(description = "Dirección del paciente", example = "Av. Las Palmas #123, Banderilla, Ver.")
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @Schema(description = "Síntomas reportados por el paciente", example = "Dolor de cabeza, fiebre, fatiga")
    private String sintomas;

    
    public Paciente() {
        super();
    }

  
    public Paciente(Long id, String curp, String direccion, String nombre, String correo, String rol, String contra, Integer edad, String numero, String sintomas) {
        super(id, numero, nombre, edad, contra, rol, correo);
        this.curp = curp;
        this.direccion = direccion;
        this.sintomas = sintomas;
    }

    
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getSintomas() { return sintomas; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }
}

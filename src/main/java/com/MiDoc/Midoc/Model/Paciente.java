package com.MiDoc.Midoc.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Schema(description = "Entidad que representa a un paciente, hereda de Usuario")
public class Paciente extends Usuario {

    @NotBlank(message = "El CURP no puede estar vacío")
    @Schema(description = "CURP del paciente", example = "GOPL920305HMCLNS08")
    private String curp;

    @ElementCollection
    @Schema(description = "Lista de alergias del paciente", example = "[\"Penicilina\", \"Mariscos\"]")
    private List<String> alergias;

    @ElementCollection
    @Schema(description = "Enfermedades crónicas del paciente", example = "[\"Diabetes\", \"Hipertensión\"]")
    private List<String> enfermedadesCronicas;

    @Schema(description = "Teléfono de contacto de emergencia", example = "2289876543")
    private String contactoEmergencia;

    public Paciente() {
        super();
    }

    public Paciente(Long id, String numero, String nombre, Integer edad, String contra, String rol, String correo, String foto_url,
                    String curp, List<String> alergias, List<String> enfermedadesCronicas, String contactoEmergencia) {
        super(id, numero, nombre, edad, contra, rol, correo, foto_url);
        this.curp = curp;
        this.alergias = alergias;
        this.enfermedadesCronicas = enfermedadesCronicas;
        this.contactoEmergencia = contactoEmergencia;
    }

    // Getters y setters
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public List<String> getAlergias() { return alergias; }
    public void setAlergias(List<String> alergias) { this.alergias = alergias; }

    public List<String> getEnfermedadesCronicas() { return enfermedadesCronicas; }
    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) { this.enfermedadesCronicas = enfermedadesCronicas; }

    public String getContactoEmergencia() { return contactoEmergencia; }
    public void setContactoEmergencia(String contactoEmergencia) { this.contactoEmergencia = contactoEmergencia; } 
}
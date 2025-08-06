package com.MiDoc.Midoc.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Schema(description = "Entidad que representa a un doctor, hereda de Usuario")
public class Doctor extends Usuario {

    @Schema(description = "Especialidad médica del doctor", example = "Cardiología")
    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

    @ElementCollection
    @Schema(description = "Lista de especialidades adicionales del doctor", example = "[\"Medicina Interna\", \"Geriatría\"]")
    private List<String> otras_especialidades;

    @NotBlank(message = "La cédula profesional no puede estar vacía")
    @Schema(description = "Cédula profesional del doctor", example = "1234567")
    private String cedula;

    @ElementCollection
    @Schema(description = "Fechas disponibles para citas", example = "[\"2025-08-10\", \"2025-08-12\"]")
    private List<String> fechasDisponibles;

    @DecimalMin(value = "0.0", inclusive = true, message = "La calificación debe ser positiva")

    @Schema(description = "Calificación promedio del doctor", example = "4.8")
    private double calificacion;

    @Schema(description = "Descripción del perfil del doctor", example = "Especialista en cardiología con 10 años de experiencia")
    private String descripcion;

    @Schema(description = "Dirección de la clínica del doctor", example = "Av. Reforma #123, CDMX")
    private String direccion;

    @DecimalMin(value = "0.0", inclusive = true, message = "El costo debe ser positivo")
    @Schema(description = "Costo por cita médica", example = "500.00")
    private double costoCita;

    @DecimalMin(value = "-90.0", message = "La latitud debe ser válida")
    @DecimalMax(value = "90.0", message = "La latitud debe ser válida")
    @Schema(description = "Latitud de la ubicación de la clínica", example = "19.4326")
    private Double latitud;

    @DecimalMin(value = "-180.0", message = "La longitud debe ser válida")
    @DecimalMax(value = "180.0", message = "La longitud debe ser válida")
    @Schema(description = "Longitud de la ubicación de la clínica", example = "-99.1332")
    private Double longitud;
  
    public Doctor() {
        super();
    }

  
    public Doctor(Long id, String numero, String nombre, Integer edad, String contra, String rol, String correo, String foto_url,
              String especialidad, String cedula, List<String> fechasDisponibles, Double calificacion,
              String descripcion, String direccion, Double costoCita, List<String> otras_especialidades, Double latitud, Double longitud) {
    super(id, numero, nombre, edad, contra, rol, correo, foto_url);
    this.especialidad = especialidad;
    this.cedula = cedula;
    this.fechasDisponibles = fechasDisponibles;
    this.calificacion = calificacion;
    this.descripcion = descripcion;
    this.direccion = direccion;
    this.costoCita = costoCita;
    this.otras_especialidades = otras_especialidades;
    this.latitud = latitud;
    this.longitud = longitud;
}



    public String getEspecialidad() {
        return especialidad;
    }

  
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    public List<String> getOtras_especialidades() {
        return otras_especialidades;
    }


    public void setOtras_especialidades(List<String> otras_especialidades) {
        this.otras_especialidades = otras_especialidades;
    }


    public String getCedula() {
        return cedula;
    }


    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public List<String> getFechasDisponibles() {
        return fechasDisponibles;
    }


    public void setFechasDisponibles(List<String> fechasDisponibles) {
        this.fechasDisponibles = fechasDisponibles;
    }


    public double getCalificacion() {
        return calificacion;
    }


    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public double getCostoCita() {
        return costoCita;
    }


    public void setCostoCita(double costoCita) {
        this.costoCita = costoCita;
    }


    public Double getLatitud() {
        return latitud;
    }


    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }


    public Double getLongitud() {
        return longitud;
    }


    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    
    
}

package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO para transferir datos del doctor")
public class DoctorDTO {

    @Schema(description = "ID del doctor", example = "1")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres.")
    @Schema(description = "Nombre completo del doctor", example = "Dr. Juan Pérez")
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria.")
    @Schema(description = "Especialidad principal", example = "Cardiología")
    private String especialidad;

    @Schema(description = "Otras especialidades", example = "[\"Geriatría\", \"Medicina Interna\"]")
    private List<String> otras_especialidades;

    @NotBlank(message = "La cédula profesional es obligatoria.")
    @Schema(description = "Cédula profesional", example = "1234567")
    private String cedula;

    @Schema(description = "Fechas disponibles para citas", example = "[\"2025-08-10\", \"2025-08-12\"]")
    private List<String> fechasDisponibles;

    @DecimalMin(value = "0.0", message = "La calificación debe ser positiva")
    @Schema(description = "Calificación promedio", example = "4.8")
    private double calificacion;

    @Schema(description = "Descripción del perfil", example = "Especialista en cardiología con 10 años de experiencia")
    private String descripcion;

    @Schema(description = "Dirección de la clínica", example = "Av. Reforma #123, CDMX")
    private String direccion;

    @DecimalMin(value = "0.0", message = "El costo debe ser positivo")
    @Schema(description = "Costo por cita médica", example = "500.00")
    private double costoCita;

    @DecimalMin(value = "-90.0", message = "Latitud inválida")
    @DecimalMax(value = "90.0", message = "Latitud inválida")
    @Schema(description = "Latitud de la clínica", example = "19.4326")
    private Double latitud;

    @DecimalMin(value = "-180.0", message = "Longitud inválida")
    @DecimalMax(value = "180.0", message = "Longitud inválida")
    @Schema(description = "Longitud de la clínica", example = "-99.1332")
    private Double longitud;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Schema(description = "Correo electrónico", example = "doctor.juan@example.com")
    private String correo;

    @NotBlank(message = "El número de celular es obligatorio.")
    @Size(min = 10, max = 15, message = "Debe tener entre 10 y 15 dígitos.")
    @Schema(description = "Número de contacto", example = "5512345678")
    private String numero;

    @Max(value = 120, message = "La edad no puede pasar de 120.")
    @Min(value = 18, message = "La edad mínima es de 18.")
    @Schema(description = "Edad del doctor", example = "45")
    private Integer edad;

    @NotBlank(message = "El rol es obligatorio.")
    @Schema(description = "Rol del usuario", example = "DOCTOR")
    private String rol;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    @Schema(description = "Contraseña de acceso", example = "miClaveSegura123")
    private String contra;

    @Schema(description = "URL de la foto de perfil", example = "https://miapp.com/fotos/doctor.jpg")
    private String foto_url;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    // Getters y setters (puedes generarlos automáticamente con tu IDE)

    
}

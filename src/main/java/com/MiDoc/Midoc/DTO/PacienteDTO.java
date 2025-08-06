package com.MiDoc.Midoc.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "DTO para transferir datos del paciente")
public class PacienteDTO {

    @Schema(description = "ID del paciente", example = "1")
    private Long id;

    @NotBlank(message = "El número no puede estar vacío")
    @Schema(description = "Número de identificación del paciente", example = "123456")
    private String numero;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre completo del paciente", example = "Ana López")
    private String nombre;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Schema(description = "Edad del paciente", example = "28")
    private Integer edad;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña del paciente", example = "miClaveSegura123")
    private String contra;

    @NotBlank(message = "El rol es obligatorio")
    @Schema(description = "Rol del usuario", example = "PACIENTE")
    private String rol;

    @Email(message = "Correo inválido")
    @Schema(description = "Correo electrónico del paciente", example = "ana.lopez@example.com")
    private String correo;

    @Schema(description = "URL de la foto de perfil", example = "https://miapp.com/fotos/ana.jpg")
    private String foto_url;

    @NotBlank(message = "El CURP no puede estar vacío")
    @Schema(description = "CURP del paciente", example = "LOAA920305HMCLNS08")
    private String curp;

    @Schema(description = "Dirección del paciente", example = "Calle 5 #45, Xalapa, Ver.")
    private String direccion;

    @Schema(description = "Lista de alergias", example = "[\"Polen\", \"Lácteos\"]")
    private List<String> alergias;

    @Schema(description = "Lista de enfermedades crónicas", example = "[\"Asma\"]")
    private List<String> enfermedadesCronicas;

    @Schema(description = "Teléfono de contacto de emergencia", example = "2281234567")
    private String contactoEmergencia;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getContra() { return contra; }
    public void setContra(String contra) { this.contra = contra; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getFoto_url() { return foto_url; }
    public void setFoto_url(String foto_url) { this.foto_url = foto_url; }

    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<String> getAlergias() { return alergias; }
    public void setAlergias(List<String> alergias) { this.alergias = alergias; }

    public List<String> getEnfermedadesCronicas() { return enfermedadesCronicas; }
    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) { this.enfermedadesCronicas = enfermedadesCronicas; }

    public String getContactoEmergencia() { return contactoEmergencia; }
    public void setContactoEmergencia(String contactoEmergencia) { this.contactoEmergencia = contactoEmergencia; }
}

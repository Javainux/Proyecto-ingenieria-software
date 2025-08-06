package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para transferencia de datos de usuario")
public class UsuarioDTO {

    @Schema(description = "ID del usuario", example = "1")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre completo del usuario", example = "Jareth Martínez")
    private String nombre;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Schema(description = "Edad del usuario", example = "25")
    private Integer edad;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña del usuario", example = "miPassword123")
    private String contra;

    @NotBlank(message = "El rol debe especificarse")
    @Schema(description = "Rol del usuario", example = "PACIENTE")
    private String rol;

    @Email(message = "El correo debe tener formato válido")
    @Schema(description = "Correo electrónico del usuario", example = "jareth@example.com")
    private String correo;

    @Schema(description = "Número telefónico del usuario", example = "2281234567")
    private String numero;

    @Schema(description = "URL de la foto de perfil del usuario", example = "https://midoc.com/perfiles/jareth.jpg")
    private String foto_url;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getFoto_url() { return foto_url; }
    public void setFoto_url(String foto_url) { this.foto_url = foto_url; }
}

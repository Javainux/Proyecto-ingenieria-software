package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para iniciar sesión")
public class LoginDTO {

    @NotBlank
    @Schema(description = "Correo electrónico del usuario", example = "jareth@example.com")
    private String correo;

    @NotBlank
    @Schema(description = "Contraseña del usuario", example = "jareth123")
    private String password;

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

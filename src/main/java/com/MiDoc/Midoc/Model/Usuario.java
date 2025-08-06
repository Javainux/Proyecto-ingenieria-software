package com.MiDoc.Midoc.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(description = "Entidad base que representa a un usuario dentro del sistema médico")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre completo del usuario", example = "Jareth Martínez")
    private String nombre;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Schema(description = "Edad del usuario", example = "25")
    private Integer edad;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña encriptada del usuario", example = "$2a$10$...")
    private String contra;

    @NotBlank(message = "El rol debe especificarse")
    @Schema(description = "Rol del usuario en el sistema", example = "DOCTOR")
    private String rol;

    @Email(message = "El correo debe tener formato válido")
    @Schema(description = "Correo electrónico del usuario", example = "jareth@example.com")
    private String correo;

    @Schema(description = "Número telefónico del usuario", example = "2281234567")
    private String numero;

    @Schema(description = "URL de la foto de perfil del usuario", example = "https://midoc.com/perfiles/jareth.jpg")
    private String foto_url;

    // Constructor vacío necesario para JPA
    public Usuario() {}

    // Constructor completo
    public Usuario(Long id, String numero, String nombre, Integer edad, String contra, String rol, String correo, String foto_url) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.edad = edad;
        this.contra = contra;
        this.rol = rol;
        this.correo = correo;
        this.foto_url = foto_url;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getContra() { return contra; }

    // Seteo de contraseña encriptada
    public void setContra(String contra) {
        this.contra = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(contra);
    }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    @Override
    public String getPassword() {
        return contra;
    }


    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public String getUsername() {
        return correo; // Spring Security usará esto como "username"
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + rol));
    }


    
}

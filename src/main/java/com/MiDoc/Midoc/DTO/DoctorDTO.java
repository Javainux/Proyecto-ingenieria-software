package com.MiDoc.Midoc.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class DoctorDTO {

   
    private Long id;

 
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres.")
    private String nombre;


    @NotBlank(message = "La especialidad es obligatoria.")
    private String especialidad;

    
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    private String correo;


    @NotBlank(message = "El número de celular es obligatorio.")
    @Size(min = 10, max = 15, message = "Debe tener entre 10 y 15 dígitos.")
    private String numero;

  
    @Max(value = 120, message = "La edad no puede pasar de 120.")
    @Min(value = 18, message = "La edad mínima es de 18.")
    private Integer edad;

  
    @NotBlank(message = "El rol es obligatorio.")
    private String rol;

   
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String contra;

    public DoctorDTO() {}

    public DoctorDTO(Long id, String nombre, String especialidad, String correo, String numero, Integer edad, String rol, String contra) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.correo = correo;
        this.numero = numero;
        this.edad = edad;
        this.rol = rol;
        this.contra = contra;
    }

    public Long getId() {
        return id;
    }


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

    // Getters y Setters — solo asegurate de que los tipos coincidan (especialmente Integer edad)

    
}


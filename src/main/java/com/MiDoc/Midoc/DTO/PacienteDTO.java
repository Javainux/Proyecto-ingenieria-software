package com.MiDoc.Midoc.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PacienteDTO {


    private Long id;

   
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres.")
    private String nombre;

 
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio.")
    private String correo;

 
    @NotBlank(message = "Los síntomas son obligatorios.")
    private String sintomas;

  
    @Max(value = 120, message = "La edad no puede pasar de 120.")
    @Min(value = 18, message = "La edad mínima es 18.")
    private Integer edad;


    @NotBlank(message = "La dirección es obligatoria.")
    private String direccion;

   
    @NotBlank(message = "El número de celular es obligatorio.")
    @Size(min = 10, max = 15, message = "Debe tener entre 10 y 15 dígitos.")
    private String numero;

 
    @NotBlank(message = "El rol es obligatorio.")
    private String rol;

    
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "Debe tener al menos 6 caracteres.")
    private String contra;

   
    @NotBlank(message = "El CURP es obligatorio.")
    private String curp;

    public PacienteDTO() {}

    public PacienteDTO(Long id, String nombre, String correo, Integer edad, String sintomas, String direccion, String numero, String rol, String contra, String curp) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.sintomas = sintomas;
        this.direccion = direccion;
        this.numero = numero;
        this.rol = rol;
        this.contra = contra;
        this.curp = curp;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    // Getters y Setters
    // (puedes dejarlos igual, solo asegúrate de que edad sea Integer y no int)

    
}

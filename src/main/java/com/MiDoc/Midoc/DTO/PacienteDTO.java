package com.MiDoc.Midoc.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PacienteDTO {

    private Long id;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
    private String nombre;

    @Email(message = "Correo invalido")
    @NotBlank(message = "El correo es obligatorio.")
    private String correo;

    @NotBlank(message = "Los sintomas son obligatorios")
    private String sintomas;

    @Min(value = 18, message = "La edad minima es de 18")
    private int edad;

    @NotBlank(message = "La direccion es obligatoria.")
    private String direccion;
    @NotBlank(message = "El numero de celular es obligatorio.")
    @Size(min = 10, max = 15, message = "El número de celular debe tener entre 10 y 15 dígitos.")
    private String numero;

    private String rol;
    private String contra;
    @NotBlank(message = "El curp es obligatorio.")
    private String curp;

    public PacienteDTO() {}

    public PacienteDTO(Long id, String nombre, String correo, int edad, String sintomas, String direccion, String numero, String rol, String contra, String curp) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.sintomas = sintomas;
        this.direccion = direccion;
        this.numero = numero;
        this.rol = rol;
        this. contra = contra;
        this.curp = curp;
    } 

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
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

    public int getEdad() { 
        return edad; 
    }

    public void setEdad(int edad) { 
        this.edad = edad; 
    }

    public String getSintomas() { 
        return sintomas; 
    }
    public void setSintomas(String sintomas) { 
        this.sintomas = sintomas; 
    }

    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getNumero(){
        return numero;
    }
    
    public void setNumero(String numero){
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

    public String getCurp(){
        return curp;
    }
    public void setCurp(String curp){
        this.curp =curp;
    }
    
}

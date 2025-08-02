package com.MiDoc.Midoc.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {


    private Long id;


    @NotBlank(message = "El número de celular es obligatorio.")
    @Size(min = 10, max = 15, message = "Debe tener entre 10 y 15 dígitos.")
    private String numero;

    @Max(value = 120, message = "La edad no puede pasar de 120.")
    @Min(value = 18, message = "La edad mínima es 18.")
    private Integer edad;

  
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50, message = "Debe tener entre 4 y 50 caracteres.")
    private String nombre;


    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "Debe tener al menos 6 caracteres.")
    private String contra;

  
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio.")
    private String correo;

   
    @NotBlank(message = "El rol es obligatorio.")
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String numero,String nombre, String contra, String correo, String rol, Integer edad) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.rol = rol;
        this.edad = edad;
    }

    // Getters y Setters (asegúrate que edad sea Integer aquí también)

    public Long getId(){ 
        return id; 
    } 
    public String getNombre(){ 
        return nombre; 
    } 
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    } 
    public String getContra(){ 
        return contra; 
    } 
    public void setContra(String contra){ 
        this.contra = contra; 
    } 
    public String getCorreo(){ 
        return correo; 
    } 
    public void setCorreo(String correo){ 
        this.correo = correo;  
    } 

    public String getRol(){
        return rol;
    }
        public void setRol(String rol){ 
                this.rol = rol; 
        } 
        public Integer getEdad(){ 
                return edad; 
        } 
        public void setEdad(Integer edad){ 
                this.edad = edad; 
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        
}

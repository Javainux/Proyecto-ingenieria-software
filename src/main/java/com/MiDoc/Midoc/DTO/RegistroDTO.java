package com.MiDoc.Midoc.DTO;

import java.util.List;

public class RegistroDTO {
    // Campos comunes
    private String nombre;
    private String correo;
    private String password; // ⚠️ Solo para pruebas
    private String rol;
    private String numero;
    private String foto_url;
    private int edad;

    // Campos de paciente
    private String curp;
    private String contactoEmergencia;
    private List<String> alergias;
    private List<String> enfermedadesCronicas;

    // Campos de doctor
    private String especialidad;
    private String cedula;
    private String descripcion;
    private String direccion;
    private Double costoCita;
    private Double latitud;
    private Double longitud;
    private List<String> otras_especialidades;
    private List<String> fechasDisponibles;

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getFoto_url() { return foto_url; }
    public void setFoto_url(String foto_url) { this.foto_url = foto_url; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public String getContactoEmergencia() { return contactoEmergencia; }
    public void setContactoEmergencia(String contactoEmergencia) { this.contactoEmergencia = contactoEmergencia; }

    public List<String> getAlergias() { return alergias; }
    public void setAlergias(List<String> alergias) { this.alergias = alergias; }

    public List<String> getEnfermedadesCronicas() { return enfermedadesCronicas; }
    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) { this.enfermedadesCronicas = enfermedadesCronicas; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Double getCostoCita() { return costoCita; }
    public void setCostoCita(Double costoCita) { this.costoCita = costoCita; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public List<String> getOtras_especialidades() { return otras_especialidades; }
    public void setOtras_especialidades(List<String> otras_especialidades) { this.otras_especialidades = otras_especialidades; }

    public List<String> getFechasDisponibles() { return fechasDisponibles; }
    public void setFechasDisponibles(List<String> fechasDisponibles) { this.fechasDisponibles = fechasDisponibles; }
}

package com.MiDoc.Midoc.DTO;

import java.util.List;

import com.MiDoc.Midoc.Model.Doctor;
import com.MiDoc.Midoc.Model.Paciente;
import com.MiDoc.Midoc.Model.Usuario;

public class UsuarioPerfilDTO {
    private String nombre;
    private String correo;
    private int edad;
    private String rol;
    private String numero;
    private String foto_url;

    // Campos de paciente
    private String curp;
    private String contactoEmergencia;
    private List<String> alergias;
    private List<String> enfermedadesCronicas;

    // Campos de doctor
    private String especialidad;
    private String cedula;
    private List<String> otras_especialidades;
    private List<String> fechasDisponibles;
    private double calificacion;
    private String descripcion;
    private String direccion;
    private double costoCita;
    private Double latitud;
    private Double longitud;

    public UsuarioPerfilDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.edad = usuario.getEdad();
        this.rol = usuario.getRol();
        this.numero = usuario.getNumero();
        this.foto_url = usuario.getFoto_url();

        if (usuario instanceof Paciente paciente) {
            this.curp = paciente.getCurp();
            this.contactoEmergencia = paciente.getContactoEmergencia();
            this.alergias = paciente.getAlergias();
            this.enfermedadesCronicas = paciente.getEnfermedadesCronicas();
        }

        if (usuario instanceof Doctor doctor) {
            this.especialidad = doctor.getEspecialidad();
            this.cedula = doctor.getCedula();
            this.otras_especialidades = doctor.getOtras_especialidades();
            this.fechasDisponibles = doctor.getFechasDisponibles();
            this.calificacion = doctor.getCalificacion();
            this.descripcion = doctor.getDescripcion();
            this.direccion = doctor.getDireccion();
            this.costoCita = doctor.getCostoCita();
            this.latitud = doctor.getLatitud();
            this.longitud = doctor.getLongitud();
        }
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public List<String> getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<String> getOtras_especialidades() {
        return otras_especialidades;
    }

    public void setOtras_especialidades(List<String> otras_especialidades) {
        this.otras_especialidades = otras_especialidades;
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


    
}

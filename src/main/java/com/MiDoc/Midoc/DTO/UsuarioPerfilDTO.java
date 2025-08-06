package com.MiDoc.Midoc.DTO;

import com.MiDoc.Midoc.Model.Usuario;

public class UsuarioPerfilDTO {
    private Long id;
    private String nombre;
    private Integer edad;
    private String rol;
    private String correo;
    private String numero;
    private String foto_url;

    public UsuarioPerfilDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.edad = usuario.getEdad();
        this.rol = usuario.getRol();
        this.correo = usuario.getCorreo();
        this.numero = usuario.getNumero();
        this.foto_url = usuario.getFoto_url();
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

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    
}

package com.MiDoc.Midoc.DTO;

public class CuentaRequestDTO {
    private String usuario;
    private String password;
    private String rol;
    private Long usuarioPerfilId;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getUsuarioPerfilId() {
        return usuarioPerfilId;
    }

    public void setUsuarioPerfilId(Long usuarioPerfilId) {
        this.usuarioPerfilId = usuarioPerfilId;
    }
}


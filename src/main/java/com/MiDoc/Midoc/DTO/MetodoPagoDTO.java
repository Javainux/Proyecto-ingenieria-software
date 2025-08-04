package com.MiDoc.Midoc.DTO;

import com.MiDoc.Midoc.Model.TipoTarjeta;

public class MetodoPagoDTO {

    private TipoTarjeta tipo;
    private String detalles;
    private Long usuarioId;
    private String numero;
    private String numeroMasked;
    public TipoTarjeta getTipo() {
        return tipo;
    }
    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }
    public String getDetalles() {
        return detalles;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNumeroMasked() {
        return numeroMasked;
    }
    public void setNumeroMasked(String numeroMasked) {
        this.numeroMasked = numeroMasked;
    }

    
    
}

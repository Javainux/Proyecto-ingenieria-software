package com.MiDoc.Midoc.Model;

import com.MiDoc.Midoc.Util.EncryptionUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class MetodoPago {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Enumerated(EnumType.STRING)
@NotBlank
private TipoTarjeta tipo;

@Size(max = 50)
private String detalles;

@Column(name = "numero")
private String numeroEncriptado;

@Transient
private String numero;


@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

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

public Usuario getUsuario() {
    return usuario;
}

public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}

public String getNumero() {
    if(numero == null && numeroEncriptado !=null){
        numero = EncryptionUtil.decrypt(numeroEncriptado);
    } 
    return numero;
}

public void setNumero(String numero) {
    this.numero = numero;
    this.numeroEncriptado = EncryptionUtil.encrypt(numero);
}

public String getNumeroMasked(){
    String numeroReal = getNumero();
    if(numeroReal != null && numeroReal.matches("\\d{16}")){
        String ultimosDigitos = numeroReal.substring(12);
        return "**** **** **** " + ultimosDigitos;
    }else {
         return "Número inválido";
    }
}
    
}

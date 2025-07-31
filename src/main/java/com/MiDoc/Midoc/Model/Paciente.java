package com.MiDoc.Midoc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Paciente extends Usuario{

    private String curp;
    private String direccion;
    private String numero;
    private String sintomas;

    public Paciente(){
        super();
    }

    public Paciente(Long id, String curp, String direccion, String nombre, String correo, String rol, String contra, int edad, String numero, String sintomas){

        super(id, nombre, edad, contra, rol, correo);
        this.curp = curp;
        this.direccion = direccion;
        this.sintomas = sintomas;
        this.numero = numero;
    }

    public String getCurp(){
        return curp;
    }

    public void setCurp(String curp){
        this.curp = curp;
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

    public String getSintomas(){
        return sintomas;
    }
    public void setSintomas(String sintomas){
        this.sintomas = sintomas;
    }
    
}

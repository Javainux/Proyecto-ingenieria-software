package com.MiDoc.Midoc.DTO;

import com.MiDoc.Midoc.Model.EstadoCita;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private Long id;
    private Long doctorId;
    private Long pacienteId;
    private Long metodoPagoId;
private String metodoPagoTipo;
private String metodoPagoNumeroMasked;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    private String motivo;
    private EstadoCita estado;

    // Getters y setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }
    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoCita getEstado() {
        return estado;
    }
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
    public String getMetodoPagoTipo() {
        return metodoPagoTipo;
    }
    public void setMetodoPagoTipo(String metodoPagoTipo) {
        this.metodoPagoTipo = metodoPagoTipo;
    }
    public String getMetodoPagoNumeroMasked() {
        return metodoPagoNumeroMasked;
    }
    public void setMetodoPagoNumeroMasked(String metodoPagoNumeroMasked) {
        this.metodoPagoNumeroMasked = metodoPagoNumeroMasked;
    }

    
}

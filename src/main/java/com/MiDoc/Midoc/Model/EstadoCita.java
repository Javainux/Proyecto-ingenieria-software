package com.MiDoc.Midoc.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoCita {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    COMPLETADA;

    @JsonCreator
    public static EstadoCita fromString(String value) {
        try {
            return EstadoCita.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("EstadoCita inválido: " + value);
        }
    }
}

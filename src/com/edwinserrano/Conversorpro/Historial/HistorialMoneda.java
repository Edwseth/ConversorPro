package com.edwinserrano.Conversorpro.Historial;

import java.time.LocalDateTime;

public class HistorialMoneda extends HistorialGeneral {
    private String monedaOrigen;
    private String monedaDestino;
    private double tasaCambio;

    public HistorialMoneda(String monedaOrigen, String monedaDestino, double cantidad, double tasaCambio, double resultado, LocalDateTime fechaHora) {
        super("Conversión de " + monedaOrigen + " a " + monedaDestino, cantidad, resultado, fechaHora);
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tasaCambio = tasaCambio;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getTasaCambio() {
        return tasaCambio;
    }
}

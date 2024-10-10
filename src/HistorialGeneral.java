import java.time.LocalDateTime;

public class HistorialGeneral {
    private String descripcion;
    private double cantidad;
    private double resultado;
    private LocalDateTime fechaHora;

    // Constructor
    public HistorialGeneral(String descripcion, double cantidad, double resultado, LocalDateTime fechaHora) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.resultado = resultado;
        this.fechaHora = fechaHora;
    }

    // Getters
    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}


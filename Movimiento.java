import java.time.LocalDateTime;
import java.time.LocalTime;

public class Movimiento {
    private float cantidad;
    private String concepto;
    private LocalDateTime fecha;

    public Movimiento(float cantidad, String concepto) {
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.fecha = LocalDateTime.now();
    }

    public String toString() {
        return "Cantidad: " + this.cantidad +
                "\nConcepto: " + this.concepto +
                "\nFecha: " + this.fecha;
    }

}

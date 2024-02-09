import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private final float cantidad;
    private final String concepto;
    private final LocalDateTime fecha;

    public Movimiento(float cantidad, String concepto) {
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.fecha = LocalDateTime.now();
    }

    public String getFechaFormateada() {
        // Creamos un formateador para la fecha
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");
        return this.fecha.format(formater);
    }

    public String toString() {
        return "Cantidad: " + this.cantidad +
                "\nConcepto: " + this.concepto +
                "\nFecha: " + this.fecha;
    }

    // --- GETTERS y SETTERS ---

    public float getCantidad() {
        return cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

}

public class CuentaBancaria {
    private final String IBAN;  // 2 letras y 22 numeros EJ: ES6621000418401234567891
    private final Persona titular;
    private float saldo;
    private int MAX_MOVS = 10;
    // Maximo 10 y luego se sobre escriben
    private Movimiento[] movimientos = new Movimiento[this.MAX_MOVS];
    private int MOV_COUNTER = 0;
    private final float CANTIDAD_SIN_NOTIFICAR = 3000;
    private final float MIN_SALDO = -50.0f;

    public CuentaBancaria(String IBAN, Persona titular, float saldo) {
        this.IBAN = IBAN;
        this.titular = titular;
        this.saldo = saldo;
    }

    public CuentaBancaria(String IBAN, Persona titular) {
        this.IBAN = IBAN;
        this.titular = titular;
    }

    public String getTitular() {
        return this.titular.getNombre() + " " + this.titular.getApellidos();
    }
    public String getNombreTitular() {
        return this.titular.getNombre();
    }

    public void ingresar(float cantidad, String concepto) {
        // Caso en el que ingresen 0€
        if (cantidad == 0) {
            System.out.println("ERROR: La cantidad no puede ser 0");
            return;
        }
        // Si meten una cantidad negativa, cojemos el valor absoluto
        if (cantidad < 0) {
            cantidad = Math.abs(cantidad);
        }

        this.saldo += cantidad;
        agregarMovimiento(cantidad, concepto);

    }

    public void retirar(float cantidad, String concepto) {
        // Caso en el que ingresen 0€
        if (cantidad == 0) {
            System.out.println("ERROR: La cantidad no puede ser 0");
            return;
        }

        // Si meten una cantidad negativa, cojemos el valor absoluto
        if (cantidad < 0) {
            cantidad = Math.abs(cantidad);
            System.out.println("AVISO: Saldo negativo");
        }

        this.saldo -= cantidad;

        // En caso de que la cuenta se quede a -50 no hacemos nadad
        if (this.saldo <= this.MIN_SALDO) {
            System.out.println("ERROR: La cuenta no puede tener -50€ o menos");
            this.saldo = -50.0f;
            return;
        }

        // Guardamos el valor en negativo para que sea mas grafico
        agregarMovimiento(cantidad * -1, concepto);
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void mostrarMovimientos() {
        for (int i = 0; i < this.MAX_MOVS; i++) {
            System.out.println("---------------------");
            if (this.movimientos[i] != null) {
                System.out.println(this.movimientos[i].toString());
            } else {
                System.out.println("Casilla vacia");
            }
        }
    }

    private void agregarMovimiento(float cantidad, String concepto) {

        if (cantidad > this.CANTIDAD_SIN_NOTIFICAR) {
            System.out.println("AVISO: Notificar a hacienda");
        }

        if (this.MOV_COUNTER == this.MAX_MOVS) this.MOV_COUNTER = 0;

        this.movimientos[this.MOV_COUNTER++] = new Movimiento(cantidad, concepto);

    }

}

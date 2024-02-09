public class CuentaBancaria {
    private final String IBAN;  // 2 letras y 22 numeros EJ: ES6621000418401234567891
    private final Persona titular;
    private float saldo;
    private final int MAX_MOVS = 10;
    // Maximo 10 y luego se sobre escriben
    private final Movimiento[] movimientos = new Movimiento[this.MAX_MOVS];
    private int MOV_COUNTER = 0;

    public CuentaBancaria(String IBAN, Persona titular, float saldo) {
        if (CuentaBancaria.validarDatos(IBAN, titular)) {
            this.IBAN = IBAN;
            this.titular = titular;
            this.saldo = saldo;
        } else {
            System.out.println("ERROR: Ha habido un error en los datos revisalos");
            this.IBAN = "";
            this.titular = null;
            this.saldo = 0;
        }
    }
    public CuentaBancaria(String IBAN, Persona titular) {
        if (CuentaBancaria.validarDatos(IBAN, titular)) {
            this.IBAN = IBAN;
            this.titular = titular;
        } else {
            System.out.println("ERROR: Ha habido un error en los datos revisalos");
            this.IBAN = "";
            this.titular = null;
        }
    }

    public void mostrarCuenta() {
        System.out.println("IBAN: " + this.IBAN);
        System.out.println("Titular: " + this.titular.getNombreCompleto());
        System.out.println("Saldo: " + this.saldo);
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
        float MIN_SALDO = -50.0f;
        if (this.saldo <= MIN_SALDO) {
            System.out.println("ERROR: La cuenta no puede tener -50€ o menos");
            this.saldo = -50.0f;
            return;
        }

        // Guardamos el valor en negativo para que sea mas grafico
        agregarMovimiento(cantidad * -1, concepto);
    }

    public void mostrarMovimientos() {
        for (int i = 0; i < this.MAX_MOVS; i++) {
            System.out.println("---------------------");
            if (this.movimientos[i] != null) {
                System.out.println("Cantidad: " + this.movimientos[i].getCantidad() +
                                    "\nConcepto: " + this.movimientos[i].getConcepto() +
                                    "\nFecha: " + this.movimientos[i].getFechaFormateada());
            } else {
                System.out.println("Casilla vacia");
            }
        }
    }

    // --- UTILS ---

    // TODO: Implementar funcion/clase para generar un IBAN
    public static String generarIBAN() {
        return null;
    }

    public static boolean validarDatos(String IBAN, Persona titular) {
        // Validar IBAN
        if (IBAN.length() != 24) return false;
        if (IBAN.charAt(0) > 'Z' && IBAN.charAt(0) < 'A') return false;
        if (IBAN.charAt(1) > 'Z' && IBAN.charAt(1) < 'A') return false;

        for (int i = 2; i < IBAN.length(); i++) {
            if (IBAN.charAt(i) < '0' && IBAN.charAt(i) > '9') return false;
        }

        // Validar titular
        if (titular.getEdad() < 18) {
            System.out.println("ERROR: Debes se mayor de edad para ser titular de una cuenta bancaria");
            return false;
        }

        return true;
    }

    private void agregarMovimiento(float cantidad, String concepto) {

        float CANTIDAD_SIN_NOTIFICAR = 3000;
        if (cantidad > CANTIDAD_SIN_NOTIFICAR) {
            System.out.println("AVISO: Notificar a hacienda");
        }

        if (this.MOV_COUNTER == this.MAX_MOVS) this.MOV_COUNTER = 0;

        this.movimientos[this.MOV_COUNTER++] = new Movimiento(cantidad, concepto);

    }

    // --- GETTERS y SETTERS
    public String getIBAN() {
        return this.IBAN;
    }
    public float getSaldo() {
        return this.saldo;
    }
    public String getTitular() {
        return this.titular.getNombreCompleto();
    }

}

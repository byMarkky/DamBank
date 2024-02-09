import java.util.Scanner;

public class DamBank {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Persona persona;
        CuentaBancaria cb;

        /*
         * Crear un menu para crear una persona y su cuenta bancaria
         * Crear un menu para las opciones de la cuenta bancaria
         * TODO: Optimizar la funcion main() (ocupa mucho)
         * TODO: Guardar los mivimientos de la cuenta en archivos
         */

        persona = new Persona("Marco", "Ramos Mejias", 18);
        cb = new CuentaBancaria("ES6621000418401234567891", persona, 0);

        int opt;

        do {
            opt = mostrarMenu(reader);  // Devolvemos la opcion seleccionada
            switch (opt){
                case 1:
                    cb.mostrarCuenta();
                    break;
                case 2:
                    System.out.println("IBAN: " + cb.getIBAN());
                    break;
                case 3:
                    System.out.println("Titular: " + cb.getTitular());
                    break;
                case 4:
                    System.out.println("Saldo: " + cb.getSaldo());
                    break;
                case 5:
                    // TODO: Implementar ingreso
                    break;
                case 6:
                    // TODO: implementra retirada
                    break;
                case 7:
                    cb.mostrarMovimientos();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("ERROR: No existe esa opcion.");
                    break;
            }

        } while (opt != 8);

        reader.close();

    }

    public static int mostrarMenu(Scanner reader) {
        int opt;
        System.out.println("1. Datos de la cuenta.");
        System.out.println("2. IBAN.");
        System.out.println("3. Titular.");
        System.out.println("4. Saldo.");
        System.out.println("5. Ingreso.");
        System.out.println("6. Retirada.");
        System.out.println("7. Movimientos.");
        System.out.println("8. Salir. Termina el programa.");
        System.out.print("Opcion -> ");
        opt = reader.nextInt();
        reader.nextLine();  // Limpieza del buffer

        return opt;
    }

}

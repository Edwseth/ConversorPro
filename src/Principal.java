import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        // Instanciar los conversores una sola vez
        Conversor conversorMonedas = new ConversorMoneda(); // Asegúrate de que el nombre coincida
        Conversor conversorMedidas = new ConversorMedidas(); // Asegúrate de que el nombre coincida

        String menu = """
                ********** Menú Principal **********
                1. Calculadora
                2. Conversor de Monedas
                3. Conversor de Medidas
                9. Salir
                ********* Elige una opción *********
                """;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    Calculadora.ejecutarCalculadora(teclado); // Asegúrate de que esta clase exista
                    break;
                case 2:
                    conversorMonedas.ejecutarConversor(teclado);
                    break;
                case 3:
                    conversorMedidas.ejecutarConversor(teclado);
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        } while (opcion != 9);
        teclado.close();
    }
}



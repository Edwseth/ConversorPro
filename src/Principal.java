import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        //Conversor conversor;

        // Instanciar los conversores una sola vez
        Conversor conversorMonedas = new ConversorMonedas();
        Conversor conversorMedidas = new ConversorMedidas();

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

            switch (opcion) {
                case 1:
                    Calculadora.ejecutarCalculadora(teclado);
                    break;
                case 2:
                    //conversor = new ConversorMonedas();
                    conversorMonedas.ejecutarConversor(teclado);
                    break;
                case 3:
                    //conversor = new ConvertidorMedidas();
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


import java.util.Scanner;

public class ConversorMonedas extends Conversor{
    @Override
    public void ejecutarConversor(Scanner teclado) {
        double cantidad, resultado;
        int opcion;

//    public static void ejecutarConversor(Scanner teclado) {
//        double cantidad, resultado;
//        int opcion;

        // Tasas de cambio estáticas (puedes actualizarlas según tu necesidad)
        double tasaUsdToEur = 0.94;
        double tasaUsdToCop = 4000.0;
        double tasaUsdToJpy = 147.0;

        String menu = """
                ********** Conversor de Monedas **********
                1. USD a EUR
                2. USD a COP
                3. USD a JPY
                4. EUR a USD
                5. COP a USD
                6. JPY a USD
                9. Volver al menú principal
                ********** Selecciona una opción **********
                """;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Introduce la cantidad a convertir: ");
                cantidad = teclado.nextDouble();

                switch (opcion) {
                    case 1: // USD a EUR
                        resultado = cantidad * tasaUsdToEur;
                        System.out.println(cantidad + " USD son " + resultado + " EUR.");
                        break;
                    case 2: // USD a COP
                        resultado = cantidad * tasaUsdToCop;
                        System.out.println(cantidad + " USD son " + resultado + " COP.");
                        break;
                    case 3: // USD a JPY
                        resultado = cantidad * tasaUsdToJpy;
                        System.out.println(cantidad + " USD son " + resultado + " JPY.");
                        break;
                    case 4: // EUR a USD
                        resultado = cantidad / tasaUsdToEur;
                        System.out.println(cantidad + " EUR son " + resultado + " USD.");
                        break;
                    case 5: // COP a USD
                        resultado = cantidad / tasaUsdToCop;
                        System.out.println(cantidad + " COP son " + resultado + " USD.");
                        break;
                    case 6: // JPY a USD
                        resultado = cantidad / tasaUsdToJpy;
                        System.out.println(cantidad + " JPY son " + resultado + " USD.");
                        break;
                }
            } else if (opcion != 9) {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }

        } while (opcion != 9);

        System.out.println("Volviendo al menú principal...");
    }
}


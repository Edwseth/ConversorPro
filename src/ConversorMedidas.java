import java.util.Scanner;

public class ConversorMedidas extends Conversor{
    @Override
    public void ejecutarConversor(Scanner teclado) {
        double cantidad, resultado;
        int opcion;

//    public static void ejecutarConversor(Scanner teclado) {
//        double valor, resultado;
//        int opcion;

        String menu = """
                ********** Conversor de Medidas **********
                1. Metros a Kilómetros
                2. Kilómetros a Metros
                3. Metros a Pies
                4. Pies a Metros
                5. Metros a Pulgadas
                6. Pulgadas a Metros
                9. Volver al menú principal
                ********** Selecciona una opción **********
                """;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Introduce el valor a convertir: ");
                cantidad = teclado.nextDouble();

                switch (opcion) {
                    case 1:
                        resultado = cantidad / 1000; // Metros a Kilómetros
                        System.out.println(cantidad + " metros son " + resultado + " kilómetros.");
                        break;
                    case 2:
                        resultado = cantidad * 1000; // Kilómetros a Metros
                        System.out.println(cantidad + " kilómetros son " + resultado + " metros.");
                        break;
                    case 3:
                        resultado = cantidad * 3.28084; // Metros a Pies
                        System.out.println(cantidad + " metros son " + resultado + " pies.");
                        break;
                    case 4:
                        resultado = cantidad / 3.28084; // Pies a Metros
                        System.out.println(cantidad + " pies son " + resultado + " metros.");
                        break;
                    case 5:
                        resultado = cantidad * 39.3701; // Metros a Pulgadas
                        System.out.println(cantidad + " metros son " + resultado + " pulgadas.");
                        break;
                    case 6:
                        resultado = cantidad / 39.3701; // Pulgadas a Metros
                        System.out.println(cantidad + " pulgadas son " + resultado + " metros.");
                        break;
                }
            } else if (opcion != 9) {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }

        } while (opcion != 9);

        System.out.println("Volviendo al menú principal...");
    }
}


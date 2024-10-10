import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculadora {
    private static final List<HistorialGeneral> historialOperaciones = new ArrayList<>();

    public static void ejecutarCalculadora(Scanner teclado) {
        double num1, num2, resultado;
        int operacion;

        String menu = """
                ********** Calculadora **********
                1. Suma
                2. Resta
                3. Multiplicación
                4. División
                5. Ver el historial
                9. Volver al menú principal
                **** Selecciona una operación ****
                """;

        do {
            System.out.println(menu);

            // Validar que la entrada es un entero
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                teclado.next(); // Descartar entrada inválida
            }
            operacion = teclado.nextInt();

            if (operacion >= 1 && operacion <= 4) {
                System.out.print("Introduce el primer número: ");

                // Validar que la entrada es un número decimal
                while (!teclado.hasNextDouble()) {
                    System.out.println("Por favor, ingresa un número válido.");
                    teclado.next(); // Descartar entrada inválida
                }
                num1 = teclado.nextDouble();

                System.out.print("Introduce el segundo número: ");
                while (!teclado.hasNextDouble()) {
                    System.out.println("Por favor, ingresa un número válido.");
                    teclado.next(); // Descartar entrada inválida
                }
                num2 = teclado.nextDouble();

                String descripcion = "";
                switch (operacion) {
                    case 1 -> {
                        resultado = num1 + num2;
                        descripcion = num1 + " + " + num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 2 -> {
                        resultado = num1 - num2;
                        descripcion = num1 + " - " + num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 3 -> {
                        resultado = num1 * num2;
                        descripcion = num1 + " * " + num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 4 -> {
                        if (num2 != 0) {
                            resultado = num1 / num2;
                            descripcion = num1 + " / " + num2;
                            System.out.println("Resultado: " + resultado);
                        } else {
                            System.out.println("Error: División por cero no permitida.");
                            continue;
                        }
                    }
                    default -> resultado = 0; // Esto no debería ocurrir, pero se inicializa para evitar errores
                }

                // Agregar la operación al historial
                historialOperaciones.add(new HistorialGeneral(descripcion, num1, resultado, LocalDateTime.now()));

            } else if (operacion == 5) {
                mostrarHistorial();
            } else if (operacion != 9) {
                System.out.println("Opción no válida. Por favor, elige una operación válida.");
            }

            // Consumir la nueva línea sobrante después de nextInt()
            teclado.nextLine();

        } while (operacion != 9);

        System.out.println("Volviendo al menú principal...");
    }

    private static void mostrarHistorial() {
        if (historialOperaciones.isEmpty()) {
            System.out.println("No hay operaciones registradas en el historial.");
        } else {
            System.out.println("********** Historial de Operaciones **********");
            for (HistorialGeneral operacion : historialOperaciones) {
                System.out.printf(
                        "Fecha y hora: %s | Descripción: %s | Resultado: %.2f\n",
                        operacion.getFechaHora(),
                        operacion.getDescripcion(),
                        operacion.getResultado()
                );
            }
            System.out.println("***********************************************");
        }
    }
}


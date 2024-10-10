package com.edwinserrano.Conversorpro.Conversores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorMedidas extends Conversor {
    private final List<HistorialGeneral> historialConversiones = new ArrayList<>();

    @Override
    public void ejecutarConversor(Scanner teclado) {
        double cantidad, resultado = 0;
        int opcion;

        String menu = """
                ********** Conversor de Medidas **********
                1. Metros a Kilómetros
                2. Kilómetros a Metros
                3. Metros a Pies
                4. Pies a Metros
                5. Metros a Pulgadas
                6. Pulgadas a Metros
                7. Ver historial de conversiones
                9. Volver al menú principal
                ********** Selecciona una opción **********
                """;

        do {
            System.out.println(menu);

            // Validar que la entrada es un entero
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                teclado.next(); // Descartar entrada inválida
            }
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Introduce el valor a convertir: ");

                // Validar que la entrada es un número decimal
                while (!teclado.hasNextDouble()) {
                    System.out.println("Por favor, ingresa un número válido.");
                    teclado.next(); // Descartar entrada inválida
                }
                cantidad = teclado.nextDouble();

                String descripcion = "";
                switch (opcion) {
                    case 1 -> {
                        resultado = cantidad / 1000; // Metros a Kilómetros
                        descripcion = cantidad + " metros a kilómetros";
                        System.out.println(cantidad + " metros son " + resultado + " kilómetros.");
                    }
                    case 2 -> {
                        resultado = cantidad * 1000; // Kilómetros a Metros
                        descripcion = cantidad + " kilómetros a metros";
                        System.out.println(cantidad + " kilómetros son " + resultado + " metros.");
                    }
                    case 3 -> {
                        resultado = cantidad * 3.28084; // Metros a Pies
                        descripcion = cantidad + " metros a pies";
                        System.out.println(cantidad + " metros son " + resultado + " pies.");
                    }
                    case 4 -> {
                        resultado = cantidad / 3.28084; // Pies a Metros
                        descripcion = cantidad + " pies a metros";
                        System.out.println(cantidad + " pies son " + resultado + " metros.");
                    }
                    case 5 -> {
                        resultado = cantidad * 39.3701; // Metros a Pulgadas
                        descripcion = cantidad + " metros a pulgadas";
                        System.out.println(cantidad + " metros son " + resultado + " pulgadas.");
                    }
                    case 6 -> {
                        resultado = cantidad / 39.3701; // Pulgadas a Metros
                        descripcion = cantidad + " pulgadas a metros";
                        System.out.println(cantidad + " pulgadas son " + resultado + " metros.");
                    }
                }

                // Agregar la conversión al historial
                historialConversiones.add(new HistorialGeneral(descripcion, cantidad, resultado, LocalDateTime.now()));

            } else if (opcion == 7) {
                mostrarHistorial();
            } else if (opcion != 9) {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }

            // Consumir la nueva línea sobrante después de nextInt()
            teclado.nextLine();

        } while (opcion != 9);

        System.out.println("Volviendo al menú principal...");
    }

    private void mostrarHistorial() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay conversiones registradas en el historial.");
        } else {
            System.out.println("********** Historial de Conversiones **********");
            for (HistorialGeneral conversion : historialConversiones) {
                System.out.printf(
                        "Fecha y hora: %s | Descripción: %s | Cantidad: %.2f | Resultado: %.2f\n",
                        conversion.getFechaHora(),
                        conversion.getDescripcion(),
                        conversion.getCantidad(),
                        conversion.getResultado()
                );
            }
            System.out.println("***********************************************");
        }
    }
}




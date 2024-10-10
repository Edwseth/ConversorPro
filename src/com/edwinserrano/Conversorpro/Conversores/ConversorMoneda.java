package com.edwinserrano.Conversorpro.Conversores;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.edwinserrano.Conversorpro.Respuesta.TasaCambioRespuesta;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ConversorMoneda extends Conversor {
    private final List<HistorialMoneda> historialConversiones = new ArrayList<>();
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/0f2b3b9cf5405a64c209aa49/latest/";

    @Override
    public void ejecutarConversor(Scanner teclado) {
        int opcion;
        do {
            opcion = mostrarMenu(teclado);
            switch (opcion) {
                case 1 -> realizarConversion("USD", "COP", teclado);
                case 2 -> realizarConversion("USD", "CLP", teclado);
                case 3 -> realizarConversion("COP", "CLP", teclado);
                case 4 -> realizarConversion("COP", "USD", teclado);
                case 5 -> realizarConversion("CLP", "COP", teclado);
                case 6 -> realizarConversion("CLP", "USD", teclado);
                case 7 -> {
                    String[] monedas = obtenerMonedas(teclado);
                    realizarConversion(monedas[0], monedas[1], teclado);
                }
                case 8 -> mostrarHistorial();
                case 9 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 9);
    }

    private int mostrarMenu(Scanner teclado) {
        String menu = """
                ********** Menú de Conversión de Monedas **********
                Consulta el listado de monedas admitidas en: 
                https://www.exchangerate-api.com/docs/supported-currencies
                1. USD a COP
                2. USD a CLP
                3. COP a CLP
                4. COP a USD
                5. CLP a COP
                6. CLP a USD
                7. Ingresar otro par de divisas
                8. Ver historial de conversiones
                9. Volver al menú principal
                ****************************************************
                """;
        System.out.println(menu);
        return teclado.nextInt();
    }

    private String[] obtenerMonedas(Scanner teclado) {
        teclado.nextLine();
        System.out.print("Ingrese la moneda base (e.g., USD): ");
        String baseCurrency = teclado.nextLine().toUpperCase();
        System.out.print("Ingrese la moneda objetivo (e.g., EUR): ");
        String targetCurrency = teclado.nextLine().toUpperCase();
        return new String[]{baseCurrency, targetCurrency};
    }

    private void realizarConversion(String baseCurrency, String targetCurrency, Scanner teclado) {
        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = teclado.nextDouble();
        if (amount <= 0) {
            System.out.println("La cantidad debe ser un número positivo. Inténtalo de nuevo.");
            return;
        }
        teclado.nextLine(); // Consumir la nueva línea

        try {
            double rate = obtenerTasaCambio(baseCurrency, targetCurrency);
            if (rate != -1) {
                double convertedAmount = amount * rate;
                System.out.printf("Cantidad convertida: %.2f %s (Tasa de cambio: %.4f %s)\n", convertedAmount, targetCurrency, rate, targetCurrency);

                // Crear un nuevo objeto Historial y agregarlo a la lista
                HistorialMoneda nuevaConversion = new HistorialMoneda(
                        baseCurrency,
                        targetCurrency,
                        amount,
                        rate,
                        convertedAmount,
                        LocalDateTime.now()
                );
                historialConversiones.add(nuevaConversion);
            } else {
                mostrarMensajeError("No se pudo obtener la tasa de cambio. Verifica las monedas ingresadas o intenta más tarde.");
            }
        } catch (Exception e) {
            mostrarMensajeError("Ocurrió un error al realizar la conversión: " + e.getMessage());
        }
    }

    private double obtenerTasaCambio(String baseCurrency, String targetCurrency) {
        String requestUrl = API_URL + baseCurrency;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                TasaCambioRespuesta tasaCambioRespuesta = gson.fromJson(response.body(), TasaCambioRespuesta.class);

                if ("success".equals(tasaCambioRespuesta.getResult())) {
                    if (tasaCambioRespuesta.getConversionRates().has(targetCurrency)) {
                        return tasaCambioRespuesta.getConversionRates().get(targetCurrency).getAsDouble();
                    } else {
                        mostrarMensajeError("Moneda objetivo no encontrada en las tasas de conversión.");
                    }
                } else {
                    mostrarMensajeError("Error desde la API: Resultado no exitoso.");
                }
            } else {
                mostrarMensajeError("Error en la solicitud API: Código de respuesta " + response.statusCode());
            }
        } catch (Exception e) {
            mostrarMensajeError("Error al conectar con la API: " + e.getMessage());
        }
        return -1;
    }

    private void mostrarHistorial() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay conversiones registradas en el historial.");
        } else {
            System.out.println("********** Historial de Conversiones **********");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            for (HistorialMoneda conversion : historialConversiones) {
                System.out.printf(
                        "Fecha y hora: %s | %s a %s | Cantidad: %.2f | Tasa: %.4f | Resultado: %.2f\n",
                        conversion.getFechaHora(),
                        conversion.getMonedaOrigen(),
                        conversion.getMonedaDestino(),
                        conversion.getCantidad(),
                        conversion.getTasaCambio(),
                        conversion.getResultado()
                );
            }
            System.out.println("***********************************************");
        }
    }

    private void mostrarMensajeError(String mensaje) {
        System.out.println(mensaje);
    }
}





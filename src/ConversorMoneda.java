import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConversorMoneda extends Conversor {
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
        teclado.nextLine();

        try {
            double rate = obtenerTasaCambio(baseCurrency, targetCurrency);
            if (rate != -1) {
                double convertedAmount = amount * rate;
                System.out.printf("Cantidad convertida: %.2f %s (Tasa de cambio: %.4f %s)\n", convertedAmount, targetCurrency, rate, targetCurrency);
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

    private void mostrarMensajeError(String mensaje) {
        System.out.println(mensaje);
    }
}





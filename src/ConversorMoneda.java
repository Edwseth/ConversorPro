import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorMoneda extends Conversor {
    // API URL template
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/0f2b3b9cf5405a64c209aa49/latest/";

    @Override
    public void ejecutarConversor(Scanner teclado) {
        System.out.print("Ingrese la moneda base (e.g., USD): ");
        String baseCurrency = teclado.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda objetivo (e.g., EUR): ");
        String targetCurrency = teclado.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = teclado.nextDouble();
        teclado.nextLine(); // Consumir la nueva línea

        double rate = obtenerTasaCambio(baseCurrency, targetCurrency);
        if (rate != -1) {
            double convertedAmount = amount * rate;
            System.out.printf("Cantidad convertida: %.2f %s\n", convertedAmount, targetCurrency);
        } else {
            System.out.println("Error al obtener la tasa de cambio. Intente más tarde.");
        }
    }

    // Método para obtener la tasa de cambio de la API
    private double obtenerTasaCambio(String baseCurrency, String targetCurrency) {
        String apiKey = "YOUR_API_KEY"; // Reemplaza con tu API Key
        String requestUrl = API_URL.replace("YOUR_API_KEY", apiKey) + baseCurrency;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parsear la respuesta JSON usando Gson
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

                // Obtener la tasa de conversión de la moneda objetivo
                JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
                return conversionRates.get(targetCurrency).getAsDouble();
            } else {
                System.out.println("Error en la solicitud API: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Retornar -1 si hay un error
    }
}

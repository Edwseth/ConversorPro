import com.google.gson.JsonObject;

public class TasaCambioRespuesta {
    private String result;
    private String base_code;
    private JsonObject conversion_rates;

    // Getters
    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return base_code;
    }

    public JsonObject getConversionRates() {
        return conversion_rates;
    }

    // Setters (opcional, si necesitas modificar los valores)
    public void setResult(String result) {
        this.result = result;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public void setConversionRates(JsonObject conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}


package dwragge;

import java.util.HashMap;

public class AlphaVantageRequestBuilder {
    private HashMap<String, String> params = new HashMap<>();
    private AlphaVantageFunction function;

    public AlphaVantageRequestBuilder withFunction(AlphaVantageFunction function) {
        params.put("function", function.getFunctionCode());
        this.function = function;
        return this;
    }

    public AlphaVantageRequestBuilder withSymbol(String symbol) {
        params.put("symbol", symbol);
        return this;
    }

    public AlphaVantageRequestBuilder withInterval(AlphaVantageInterval interval) {
        params.put("interval", interval.toString());
        return this;
    }

    public AlphaVantageRequest build() {
        return new AlphaVantageRequest(params, function);
    }
}

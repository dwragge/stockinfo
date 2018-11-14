package dwragge;

import java.util.HashMap;

import static java.util.stream.Collectors.joining;

public class AlphaVantageRequestBuilder {
    private HashMap<String, String> params = new HashMap<>();

    public AlphaVantageRequestBuilder withFunction(AlphaVantageFunction function) {
        params.put("function", function.getFunctionCode());
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
        return new AlphaVantageRequest(params);
    }
}

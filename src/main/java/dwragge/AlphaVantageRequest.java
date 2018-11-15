package dwragge;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class AlphaVantageRequest {
    private static final String BASE_ENDPOINT = "https://www.alphavantage.co/query?";
    private final AlphaVantageFunction function;
    private final Map<String, String> paramsMap;

    AlphaVantageRequest(Map<String, String> paramsMap, AlphaVantageFunction function) {
        this.paramsMap = paramsMap;
        this.function = function;
    }

    public String createRequestString(String apiKey) {
        // build optimistically, assume will throw upstream (by 400) if invalid
        String paramsString =  paramsMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(joining("&"));
        paramsString += "&apikey=" + apiKey;

        return BASE_ENDPOINT + paramsString;
    }

    public AlphaVantageFunction function() {
        return function;
    }

    public static AlphaVantageRequestBuilder newBuilder() {
        return new AlphaVantageRequestBuilder();
    }
}

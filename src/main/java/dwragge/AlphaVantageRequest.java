package dwragge;


import org.immutables.value.Value;

import java.util.Map;

import static java.util.stream.Collectors.joining;

@Value.Immutable
public class AlphaVantageRequest {
    private static final String BASE_ENDPOINT = "https://www.alphavantage.co/query?";
    private final Map<String, String> paramsMap;

    AlphaVantageRequest(Map<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public String getRequestString(String apiKey) {
        // build optimistically, assume will throw upstream (by 400) if invalid
        String paramsString =  paramsMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(joining("&"));
        paramsString += "&apikey=" + apiKey;

        return BASE_ENDPOINT + paramsString;
    }

    public static AlphaVantageRequestBuilder newBuilder() {
        return new AlphaVantageRequestBuilder();
    }
}

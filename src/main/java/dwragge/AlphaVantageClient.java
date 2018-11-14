package dwragge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

@Singleton
public class AlphaVantageClient {
    private final HttpClientAdaptor httpClient;
    private static String apiKey;

    public static void setApiKey(String newApiKey) {
        apiKey = newApiKey;
    }

    @Inject
    public AlphaVantageClient(HttpClientAdaptor httpClient) {
        this.httpClient = httpClient;
    }

    public String executeRawRequest(AlphaVantageRequest request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(request.getRequestString(apiKey)))
                .build();

        return httpClient.executeRequest(httpRequest);
    }

    public <T> T executeRequest(AlphaVantageRequest request, Class<? extends AlphaVantageResponseClass> deserializeType) {
        String responseBody = executeRawRequest(request);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            return mapper.readerFor(deserializeType).readValue(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

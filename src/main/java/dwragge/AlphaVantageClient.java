package dwragge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

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

    public <T extends AlphaVantageResponseClass> Optional<T> executeRequest(AlphaVantageRequest request, Class<T> responseType) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(request.createRequestString(apiKey)))
                .build();

        try {
            var responseBody = httpClient.executeRequest(httpRequest);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            // is this really unchecked? how else to do it
            T value = mapper.readerFor(responseType).readValue(responseBody);
            return Optional.of(value);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

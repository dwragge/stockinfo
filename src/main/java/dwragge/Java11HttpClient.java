package dwragge;

import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Prototype
public class Java11HttpClient implements HttpClientAdaptor {
    private final HttpClient httpClient;
    private final Logger logger = LoggerFactory.getLogger(Java11HttpClient.class);

    public Java11HttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public byte[] executeRequest(HttpRequest request) {
        logger.info(String.format("Beginning request to %s", request.uri()));

        long startTime = System.nanoTime();
        HttpResponse<byte[]> responseBody = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())
                .join();
        long endTime = System.nanoTime();

        logger.info(String.format("Returned %d in %s", responseBody.statusCode(), TimeHelper.PrettyPrintNanoTime(endTime - startTime)));

        return responseBody.body();
    }
}

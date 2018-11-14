package dwragge;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HealthCheckControllerTest {
    private static EmbeddedServer embeddedServer;
    private static HttpClient client;

    @BeforeClass
    public static void Setup() {
        embeddedServer = ApplicationContext.run(EmbeddedServer.class);
        client = HttpClient.create(embeddedServer.getURL());
    }

    @Test
    public void HealthCheckController_HealthCheck_ShouldReturnOnline() {
        String response = client.toBlocking().retrieve(HttpRequest.GET("/healthcheck"));
        assertEquals("Online", response);
    }
}

package dwragge;

import org.junit.Test;

import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class Java11HttpClientTests {
    @Test
    public void Java11HttpClient_SimpleWebPage_CanDownload() {
        HttpClientAdaptor client = new Java11HttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();

        byte[] response = client.executeRequest(request);
        String responseString = new String(response, Charset.forName("UTF-8"));
        String expected = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"delectus aut autem\",\n" +
                "  \"completed\": false\n" +
                "}";

        assertEquals(expected, responseString);
    }
}

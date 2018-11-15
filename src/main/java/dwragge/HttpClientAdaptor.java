package dwragge;

import java.net.http.HttpRequest;

public interface HttpClientAdaptor {
    byte[] executeRequest(HttpRequest request);
}

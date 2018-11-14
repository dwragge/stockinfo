package dwragge;

import java.net.http.HttpRequest;

public interface HttpClientAdaptor {
    String executeRequest(HttpRequest request);
}

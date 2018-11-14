package dwragge;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;


@Controller("/healthcheck")
public class HealthCheckController {
    @Get(produces = MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "Online";
    }
}

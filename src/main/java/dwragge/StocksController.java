package dwragge;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/stocks")
public class StocksController {
    private final AlphaVantageClient client;

    @Inject
    public StocksController(AlphaVantageClient client) {
        this.client = client;
    }

    @Get("/{symbol}/daily")
    public String GetDailyData(String symbol) {
        AlphaVantageRequest request = new AlphaVantageRequestBuilder()
                .withFunction(AlphaVantageFunction.TIMESERIES_DAILY)
                .withSymbol(symbol)
                .build();

        return client.executeRawRequest(request);
    }
}

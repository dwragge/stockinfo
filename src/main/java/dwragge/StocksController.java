package dwragge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpResponse.serverError;

@Controller("/stocks")
public class StocksController {
    private final AlphaVantageClient client;
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());

    @Inject
    public StocksController(AlphaVantageClient client) {
        this.client = client;
    }

    @Get("/{symbol}/daily")
    public HttpResponse<String> GetDailyData(String symbol) {
        AlphaVantageRequest request = new AlphaVantageRequestBuilder()
                .withFunction(AlphaVantageFunction.TIMESERIES_DAILY)
                .withSymbol(symbol)
                .build();

        var response = client.executeRequest(request, AlphaVantageFunction.TIMESERIES_DAILY.responseClassType()).orElseThrow();
        try {
            return ok(mapper.writer().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            return serverError(e.getMessage());
        }
    }

    @Get("/{symbol}/averageopen")
    public HttpResponse<Double> GetAverageOpen(String symbol) {
        var request = AlphaVantageRequest.newBuilder()
                .withFunction(AlphaVantageFunction.TIMESERIES_DAILY)
                .withSymbol(symbol)
                .build();

        TimeSeriesData data = client.executeRequest(request, TimeSeriesData.class).orElseThrow();
        var sumService = new StockStatsProvider();
        return ok(sumService.SummariseTimeSeries(data));
    }
}

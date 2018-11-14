package dwragge;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.net.http.HttpRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AlphaVantageClientTests {
    private static final String apiKey = "test";
    @Test
    public void AlphaVantageRequestBuilder_BasicUsage_ShouldBuildRequest() {
        AlphaVantageRequest request = AlphaVantageRequest.newBuilder()
                .withFunction(AlphaVantageFunction.TIMESERIES_DAILY)
                .withSymbol("MSFT")
                .build();

        String expectedUrl = "https://www.alphavantage.co/query?symbol=MSFT&function=TIME_SERIES_DAILY&apikey=test";
        assertEquals(expectedUrl, request.getRequestString(apiKey));
    }

    @Test
    public void AlphaVantageRequestBuilder_FunctionWithInterval_ShouldBuildRequest() {
        AlphaVantageRequest request = AlphaVantageRequest.newBuilder()
                .withSymbol("AAPL")
                .withFunction(AlphaVantageFunction.TIMESERIES_INTRADAY)
                .withInterval(AlphaVantageInterval.FIFTEEN_MIN)
                .build();

        String expectedUrl = "https://www.alphavantage.co/query?symbol=AAPL&function=TIME_SERIES_INTRADAY&interval=15min&apikey=test";
        assertEquals(expectedUrl, request.getRequestString(apiKey));
    }

    @Test
    public void AlphaVantageClient_ExecuteRequest_MapsCorrectly() {
        AlphaVantageRequest request = AlphaVantageRequest.newBuilder()
                .withSymbol("MSFT")
                .withFunction(AlphaVantageFunction.TIMESERIES_DAILY)
                .build();
        String stubResponse = "{\n" +
                "  \"Meta Data\": {\n" +
                "    \"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",\n" +
                "    \"2. Symbol\": \"MSFT\",\n" +
                "    \"3. Last Refreshed\": \"2018-11-13\",\n" +
                "    \"4. Output Size\": \"Compact\",\n" +
                "    \"5. Time Zone\": \"US/Eastern\"\n" +
                "  },\n" +
                "  \"Time Series (Daily)\": {\n" +
                "    \"2018-11-13\": {\n" +
                "      \"1. open\": \"107.5500\",\n" +
                "      \"2. high\": \"108.7400\",\n" +
                "      \"3. low\": \"106.6400\",\n" +
                "      \"4. close\": \"106.9400\",\n" +
                "      \"5. volume\": \"35360648\"\n" +
                "    },\n" +
                "    \"2018-11-12\": {\n" +
                "      \"1. open\": \"109.4200\",\n" +
                "      \"2. high\": \"109.9600\",\n" +
                "      \"3. low\": \"106.1000\",\n" +
                "      \"4. close\": \"106.8700\",\n" +
                "      \"5. volume\": \"33621807\"\n" +
                "    }}}";

        HttpClientAdaptor mocked = mock(HttpClientAdaptor.class);
        when(mocked.executeRequest(any(HttpRequest.class))).thenReturn(stubResponse);
        AlphaVantageClient client = new AlphaVantageClient(mocked);

        TimeSeriesData data = client.executeRequest(request, AlphaVantageFunction.TIMESERIES_DAILY.getResponseClassType());
        assertEquals(2, data.seriesData().size());
        assertEquals(33621807L, data.seriesData().get(LocalDate.parse("2018-11-12")).volume());
    }
}

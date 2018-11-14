package dwragge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeSeriesPointTests {
    @Test
    public void TimeSeriesPoint_Daily_ShouldDeserialize() throws Exception {
        String json = "{\n" +
                "      \"1. open\": \"107.5500\",\n" +
                "      \"2. high\": \"108.7400\",\n" +
                "      \"3. low\": \"106.6400\",\n" +
                "      \"4. close\": \"106.9400\",\n" +
                "      \"5. volume\": \"35360648\"\n" +
                "    }";
        ObjectMapper mapper = new ObjectMapper();
        var point = mapper.readValue(json, TimeSeriesPoint.class);

        assertEquals(107.55d, point.open(),0.01);
        assertEquals(108.74d, point.high(), 0.01);
        assertEquals(106.64d, point.low(), 0.01);
        assertEquals(106.94d, point.close(), 0.01);
        assertEquals(35360648L, point.volume());
    }

    @Test
    public void TimeSeriesData_Daily_ShouldDeserialize() throws Exception {
        String json = "{\n" +
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

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        var data = mapper.readValue(json, TimeSeriesData.class);
        assertEquals(2, data.seriesData().size());
        assertEquals(35360648L, data.seriesData().get(LocalDate.parse("2018-11-13")).volume());
    }
}

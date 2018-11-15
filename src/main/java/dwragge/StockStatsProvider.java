package dwragge;

import org.joda.time.LocalDate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockStatsProvider {
    public double SummariseTimeSeries(TimeSeriesData data) {
        return data.seriesData().entrySet().stream()
                .filter(x -> x.getKey().isAfter(LocalDate.now().minusDays(10)))
                .mapToDouble(x -> x.getValue().open())
                .average().orElseThrow();
    }
}

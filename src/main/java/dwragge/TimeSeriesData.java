package dwragge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.joda.time.LocalDate;

import java.util.Map;

@Value.Immutable
@JsonDeserialize(as = ImmutableTimeSeriesData.class)
@JsonSerialize(as = ImmutableTimeSeriesData.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface TimeSeriesData extends AlphaVantageResponseClass {
    @JsonProperty("Time Series (Daily)")
    Map<LocalDate, TimeSeriesPoint> seriesData();
}

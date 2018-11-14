package dwragge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTimeSeriesPoint.class)
@JsonDeserialize(as = ImmutableTimeSeriesPoint.class)
public interface TimeSeriesPoint {
    @JsonProperty("1. open") double open();
    @JsonProperty("2. high") double high();
    @JsonProperty("3. low") double low();
    @JsonProperty("4. close") double close();
    @JsonProperty("5. volume") long volume();
}

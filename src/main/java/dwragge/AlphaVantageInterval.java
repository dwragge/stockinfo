package dwragge;

public enum AlphaVantageInterval {
    FIFTEEN_MIN("15min");

    private final String intervalString;
    AlphaVantageInterval(String intervalString) {
        this.intervalString = intervalString;
    }

    @Override
    public String toString() {
        return intervalString;
    }
}

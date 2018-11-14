package dwragge;

public enum AlphaVantageFunction {
    TIMESERIES_DAILY("TIME_SERIES_DAILY", TimeSeriesData.class),
    TIMESERIES_INTRADAY("TIME_SERIES_INTRADAY", TimeSeriesData.class);


    private final String functionCode;
    private final Class<? extends AlphaVantageResponseClass> responseClassType;
    AlphaVantageFunction(String functionCode, Class<? extends AlphaVantageResponseClass> responseClassType) {
        this.functionCode = functionCode;
        this.responseClassType = responseClassType;
    }

    public String getFunctionCode() {
        return  functionCode;
    }

    public Class<? extends AlphaVantageResponseClass> getResponseClassType() {
        return responseClassType;
    }
}

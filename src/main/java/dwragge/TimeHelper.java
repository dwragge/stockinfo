package dwragge;

import java.text.DecimalFormat;

public class TimeHelper {
    public static String PrettyPrintNanoTime(long nanoTime) {
        String suffix = "ms";
        double value = nanoTime / 1000000.d;

        if (value > 1000) {
            suffix = "s";
            value /= 1000.d;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value) + suffix;
    }
}

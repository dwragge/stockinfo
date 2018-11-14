package dwragge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeHelperTests {
    @Test
    public void TimeHelper_ValueUnder1s_ShouldPrintMs() {
        long nanoTime = 253000000;
        String prettyPrinted = TimeHelper.PrettyPrintNanoTime(nanoTime);
        assertEquals("253ms", prettyPrinted);
    }

    @Test
    public void TimeHelper_ValueOver1s_ShouldPrintS() {
        long nanoTime = 2530000000L;
        String prettyPrinted = TimeHelper.PrettyPrintNanoTime(nanoTime);
        assertEquals("2.53s", prettyPrinted);
    }

    @Test
    public void TimeHelper_ValueWithManyDP_ShouldRound() {
        long nanoTime = 2535005678L;
        String prettyPrinted = TimeHelper.PrettyPrintNanoTime(nanoTime);
        assertEquals("2.54s", prettyPrinted);
    }
}

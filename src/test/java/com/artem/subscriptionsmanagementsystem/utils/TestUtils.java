package com.artem.subscriptionsmanagementsystem.utils;

import static com.artem.subscriptionsmanagementsystem.util.DateUtil.stringifyPeriod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TestUtils {

    public static final String EXPECTED_PERIOD = "5 years 1 month 13 days";

    public static final String YEARS = "5 year";
    public static final String MONTH = "1 month";
    public static final String DAYS = "13 day";

    public static final int COUNT_YEARS = 5;
    public static final int COUNT_DAYS = 13;
    public static final int COUNT_MONTH = 1;

    @Test
    void testDuration() {
        var startTime = LocalDate.now();
        var endTime = LocalDate.now().plusYears(COUNT_YEARS)
            .plusDays(COUNT_DAYS)
            .plusMonths(COUNT_MONTH);

        var period = stringifyPeriod(startTime, endTime);
        assertEquals(EXPECTED_PERIOD, period);
        assertTrue(period.contains(YEARS));
        assertTrue(period.contains(MONTH));
        assertTrue(period.contains(DAYS));
    }
}

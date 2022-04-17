package com.artem.subscriptionsmanagementsystem.utils;

import static com.artem.subscriptionsmanagementsystem.util.DateUtil.stringifyPeriod;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TestUtils {

    @Test
    void testDuration(){
        var startTime = LocalDate.now();
        var endTime = LocalDate.now().plusYears(5).plusDays(13).plusMonths(1);
        var leftPeriod = stringifyPeriod(startTime, endTime);
        System.out.println(leftPeriod);
    }
}

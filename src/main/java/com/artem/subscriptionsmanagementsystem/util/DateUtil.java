package com.artem.subscriptionsmanagementsystem.util;

import java.time.LocalDate;
import java.time.ZoneId;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatterBuilder;

public final class DateUtil {

    public static String stringifyPeriodFromNow(LocalDate endTime) {
        var startMills = getMills(LocalDate.now());
        var endMills = getMills(endTime);

        Period period = new Period(startMills, endMills);
        return printPeriod(period);
    }

    public static String stringifyPeriod(LocalDate startTime, LocalDate endTime) {
        var startMills = getMills(startTime);
        var endMills = getMills(endTime);

        Period period = new Period(startMills, endMills);
        return printPeriod(period);
    }

    private static long getMills(LocalDate endTime) {
        return endTime.atStartOfDay().atZone(ZoneId.of("EET")).toInstant().toEpochMilli();
    }

    private static String printPeriod(Period period) {
        return new PeriodFormatterBuilder()
            .appendYears().appendSuffix(" year", " years")
            .appendSeparator(" ")
            .appendMonths().appendSuffix(" month", " months")
            .appendSeparator(" ")
            .appendDays().appendSuffix(" day", " days")
            .appendSeparator(" ")
            .toFormatter()
            .print(period.normalizedStandard(PeriodType.yearMonthDay()));
    }
}

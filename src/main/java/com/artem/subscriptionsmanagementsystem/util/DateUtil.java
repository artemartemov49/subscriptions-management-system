package com.artem.subscriptionsmanagementsystem.util;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import java.time.LocalDate;
import java.time.ZoneId;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

public final class DateUtil {

    public static String stringifyPeriod(LocalDate startTime, LocalDate endTime) {
        var startMills = startTime.atStartOfDay().atZone(ZoneId.of("EET")).toInstant().toEpochMilli();
        var endMills = endTime.atStartOfDay().atZone(ZoneId.of("EET")).toInstant().toEpochMilli();

        Period period = new Period(startMills, endMills);
        var formatter = new PeriodFormatterBuilder()
            .appendYears().appendSuffix(" year", " years")
            .appendSeparator(" ")
            .appendMonths().appendSuffix(" month", " months")
            .appendSeparator(" ")
            .appendDays().appendSuffix(" day", " days")
            .appendSeparator(" ")
            .printZeroIfSupported().minimumPrintedDigits(2)
            .toFormatter();

        return formatter.print(period);
    }
}

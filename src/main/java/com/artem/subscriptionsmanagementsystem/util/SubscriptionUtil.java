package com.artem.subscriptionsmanagementsystem.util;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class SubscriptionUtil {

    public static void setStatus(Subscription object) {
        if (object.getEndTime().isBefore(LocalDate.now())) {
            object.setStatus(Status.DISABLED);
        }
    }

    public static String getPeriod(Subscription object) {
        String period = "ended";

        if (object.getStatus().equals(Status.ACTIVE)) {
            period = DateUtil.stringifyPeriodFromNow(object.getEndTime());
        }

        return period;
    }
}

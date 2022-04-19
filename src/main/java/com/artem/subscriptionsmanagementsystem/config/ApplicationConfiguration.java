package com.artem.subscriptionsmanagementsystem.config;

import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration()
@EnableScheduling
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Autowired
    SubscriptionService subscriptionService;

    /**
     * Scheduled service updates Subscription's status every minute (could be changed)
     * If Subscription's endTime isBefore LocalDate.now() then service sets status to DISABLED
     */
    @Scheduled(cron = "0 * * * * *")
    public void updateSubscriptionsStatus() {
        System.out.println("Staring update status for subscriptions");
        subscriptionService.updateStatusForAll();
        System.out.println("End updating status for subscriptions");
    }
}

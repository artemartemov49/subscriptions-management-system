package com.artem.subscriptionsmanagementsystem.config;

import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration()
@EnableScheduling
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Autowired
    SubscriptionService subscriptionService;

    /**
     * Scheduled service updates Subscription's status every minute (could be changed) If Subscription's endTime
     * isBefore LocalDate.now() then service sets status to DISABLED
     */
    @Scheduled(cron = "0 * * * * *")
    public void updateSubscriptionsStatus() {
        log.debug("Staring update status for subscriptions");
        subscriptionService.updateStatusForAll();
        log.debug("End updating status for subscriptions");
    }
}

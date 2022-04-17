package com.artem.subscriptionsmanagementsystem.integration.service;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.DISABLED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.integration.IntegrationTestBase;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class SubscriptionServiceIT extends IntegrationTestBase {

    private final SubscriptionService subscriptionService;
    private final UserRepository userRepository;

    @Test
    void create() {
        var subscriptionDto = new SubscriptionCreateDto(1, 1);
        var actualResult = subscriptionService.create(subscriptionDto);

        var subscriptions = userRepository.findById(subscriptionDto.getUserId())
            .map(User::getSubscriptions)
            .orElseThrow();

        var userId = subscriptions.stream()
            .filter(it -> it.getId().equals(actualResult.getId()))
            .map(Subscription::getUser)
            .map(User::getId)
            .findFirst()
            .orElseThrow();

        assertEquals(subscriptionDto.getItemId(), actualResult.getItem().getId());
        assertEquals(subscriptionDto.getUserId(), userId);
        assertEquals(LocalDate.now().minusMonths(2), actualResult.getStartTime());
//        assertEquals(LocalDate.now().minusMonths(1), actualResult.getEndTime());
        assertEquals(DISABLED, actualResult.getStatus());
    }
}

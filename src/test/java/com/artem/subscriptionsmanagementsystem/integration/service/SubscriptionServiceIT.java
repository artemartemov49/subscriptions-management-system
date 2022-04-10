package com.artem.subscriptionsmanagementsystem.integration.service;

import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.integration.IntegrationTestBase;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class SubscriptionServiceIT extends IntegrationTestBase {

    private final SubscriptionService subscriptionService;

    @Test
    void create() {
        var orderCreateDto = new OrderCreateDto(null, 1);

        var userDto = new SubscriptionCreateEditDto(
            1,
            1,
            orderCreateDto
        );
        var actualResult = subscriptionService.create(userDto);
    }
}

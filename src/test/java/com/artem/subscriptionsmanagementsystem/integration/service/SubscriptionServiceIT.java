package com.artem.subscriptionsmanagementsystem.integration.service;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.DISABLED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.integration.IntegrationTestBase;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class SubscriptionServiceIT extends IntegrationTestBase {

    private final SubscriptionService subscriptionService;
    private final OrderService orderService;
    private final SubscriptionRepository subscriptionRepository;
    private final OrderRepository orderRepository;

    public static final int USER_ID = 1;
    public static final int PRICE_ID = 1;

    @Test
    void create() {
        var subscriptionDto = new SubscriptionCreateDto(USER_ID, USER_ID);
        var actualResult = subscriptionService.create(subscriptionDto);

        var userId = subscriptionRepository.findById(actualResult.getId())
            .orElseThrow()
            .getUser()
            .getId();

        assertEquals(subscriptionDto.getPriceId(), actualResult.getItem().getId());
        assertEquals(subscriptionDto.getUserId(), userId);
        assertEquals(LocalDate.now().minusMonths(2), actualResult.getStartTime());
        assertEquals(LocalDate.now().minusMonths(1), actualResult.getEndTime());
        assertEquals(DISABLED, actualResult.getStatus());
    }

    @Test
    void createWithOrder() {
        //Creating subscription
        var subscriptionDto = new SubscriptionCreateDto(USER_ID, PRICE_ID);
        var subscriptionReadDto = subscriptionService.create(subscriptionDto);

        //Creating order
        var orderCreateDto = new OrderCreateDto(subscriptionReadDto.getId(), PRICE_ID);
        var actualResult = orderService.create(orderCreateDto);

        var subscription = orderRepository.findById(actualResult.getId())
            .map(Order::getSubscription)
            .orElseThrow();
        var months = actualResult.getPrice().getDuration().getMonths();

        assertEquals(orderCreateDto.getPriceId(), actualResult.getPrice().getId());
        assertEquals(orderCreateDto.getSubscriptionId(), subscription.getId());
        assertEquals(Status.ACTIVE, subscription.getStatus());
        assertEquals(LocalDate.now(), subscription.getStartTime());
        assertEquals(LocalDate.now().plusMonths(months), subscription.getEndTime());
    }
}

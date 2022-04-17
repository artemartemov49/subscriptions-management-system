package com.artem.subscriptionsmanagementsystem.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.order.SubscriptionWithOrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.integration.IntegrationTestBase;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class OrderServiceIT extends IntegrationTestBase {

    public static final int SUBSCRIPTION_ID = 1;
    public static final int USER_ID = 1;
    public static final int PRICE_ID = 1;
    public static final LocalDate START_TIME = LocalDate.of(2022, 4, 10);
    public static final LocalDate END_TIME = LocalDate.of(2022, 5, 10);

    private final OrderService orderService;
    private final SubscriptionService subscriptionService;
    private final OrderRepository orderRepository;

    @Test
    void create() {
        var orderCreateDto = new OrderCreateDto(SUBSCRIPTION_ID, PRICE_ID);
        var actualResult = orderService.create(orderCreateDto);

        var subscription = orderRepository.findById(actualResult.getId())
            .map(Order::getSubscription)
            .orElseThrow();
        var months = actualResult.getPrice().getDuration().getMonths();

        assertEquals(orderCreateDto.getPriceId(), actualResult.getPrice().getId());
        assertEquals(orderCreateDto.getSubscriptionId(), subscription.getId());
        assertEquals(Status.ACTIVE, subscription.getStatus());
        assertEquals(START_TIME, subscription.getStartTime());
        assertEquals(END_TIME.plusMonths(months), subscription.getEndTime());
    }

    @Test
    void createToNewSubscription() {
        var subscriptionDto = new SubscriptionCreateDto(USER_ID, PRICE_ID);
        var subscriptionReadDto = subscriptionService.create(subscriptionDto);

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

//    @Test
//    void createWithNewSubscription() {
//        var subscriptionDto = new SubscriptionWithOrderCreateDto(USER_ID,1, PRICE_ID);
//        OrderReadDto actualResult = orderService.createWithNewSubscription(subscriptionDto);
//
//        var subscription = orderRepository.findById(actualResult.getId())
//            .map(Order::getSubscription)
//            .orElseThrow();
//        var months = actualResult.getPrice().getDuration().getMonths();
//
//        assertEquals(subscriptionDto.getPriceId(), actualResult.getPrice().getId());
//        assertEquals(Status.ACTIVE, subscription.getStatus());
//        assertEquals(LocalDate.now(), subscription.getStartTime());
//        assertEquals(LocalDate.now().plusMonths(months), subscription.getEndTime());
//    }
}

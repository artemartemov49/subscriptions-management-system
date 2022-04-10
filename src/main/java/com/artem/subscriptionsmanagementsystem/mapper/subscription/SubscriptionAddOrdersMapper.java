package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionAddOrderDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionAddOrdersAllFieldsDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionAddOrdersMapper implements Mapper<SubscriptionAddOrdersAllFieldsDto, Subscription> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public Subscription map(SubscriptionAddOrdersAllFieldsDto object) {
        Subscription subscription = new Subscription();

        var orders = createOrders(object.getSubscriptionAddOrderDto());

        subscription.setId(object.getSubscriptionAddOrderDto().getId());
        subscription.setOrders(orders);
        subscription.setStatus(object.getStatus());
        subscription.setStartTime(object.getStartTime());
        subscription.setEndTime(object.getEndTime());

        return subscription;
    }

    private List<Order> createOrders(SubscriptionAddOrderDto object) {
        orderService.create(object.getOrder());

        return orderRepository.findAllBySubscriptionId(object.getId());
    }
}

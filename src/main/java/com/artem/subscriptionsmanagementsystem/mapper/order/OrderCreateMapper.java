package com.artem.subscriptionsmanagementsystem.mapper.order;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreateMapper implements Mapper<OrderCreateDto, Order> {

    private final SubscriptionRepository subscriptionRepository;
    private final PriceRepository priceRepository;

    @Override
    public Order map(OrderCreateDto object) {
        var order = new Order();
        copy(object, order);

        return order;
    }

    @Override
    public Order map(OrderCreateDto fromObject, Order toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(OrderCreateDto object, Order order) {
        var price = getPrice(object);
        var subscription = getSubscription(object);

        order.setPrice(price);
        order.setSubscription(subscription);
    }

    private Price getPrice(OrderCreateDto object) {
        return priceRepository.findById(object.getPriceId())
            .orElseThrow();
    }

    private Subscription getSubscription(OrderCreateDto object) {
        return subscriptionRepository.findById(object.getSubscriptionId())
            .orElseThrow();
    }

}

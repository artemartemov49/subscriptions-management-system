package com.artem.subscriptionsmanagementsystem.mapper.order;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.ACTIVE;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import java.time.LocalDate;
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

    private void copy(OrderCreateDto object, Order order) {
        var price = getPrice(object);
        var subscription = getSubscription(object);
        addDuration(price, subscription);

        order.setPrice(price);
        order.setSubscription(subscription);
    }

    private void addDuration(Price price, Subscription subscription) {
        var months = getMonths(price);
        var endTime = subscription.getEndTime();

        if (endTime.isBefore(LocalDate.now())) {
            setTimeFromNow(subscription, months);
        } else {
            subscription.setEndTime(endTime.plusMonths(months));
        }
    }

    private void setTimeFromNow(Subscription subscription, Integer months) {
        subscription.setStartTime(LocalDate.now());
        subscription.setEndTime(LocalDate.now().plusMonths(months));
        subscription.setStatus(ACTIVE);
    }

    private Price getPrice(OrderCreateDto object) {
        return priceRepository.findById(object.getPriceId())
            .orElseThrow();
    }

    private Subscription getSubscription(OrderCreateDto object) {
        return subscriptionRepository.findById(object.getSubscriptionId())
            .orElseThrow();
    }

    private Integer getMonths(Price price) {
        return price.getDuration().getMonths();
    }

}

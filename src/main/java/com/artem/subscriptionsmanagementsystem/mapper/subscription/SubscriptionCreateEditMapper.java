package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.ACTIVE;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreateEditMapper implements Mapper<SubscriptionCreateEditDto, Subscription> {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public Subscription map(SubscriptionCreateEditDto object) {
        var subscription = new Subscription();
        copy(object, subscription);

        var orders = createOrders(object);
        var months = orderService.getMonths(orders);

        subscription.setOrders(orders);
        subscription.setStatus(ACTIVE);
        subscription.setStartTime(LocalDate.now());
        subscription.setEndTime(LocalDate.now().plusMonths(months));

        return subscription;
    }

    @Override
    public Subscription map(SubscriptionCreateEditDto fromObject, Subscription toObject) {
        var subscription = new Subscription();
        copy(fromObject, toObject);

        return null;
    }

    private void copy(SubscriptionCreateEditDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);

        subscription.setItem(item);
        subscription.setUser(user);
    }

    private User getUser(SubscriptionCreateEditDto object) {
        return userRepository.findById(object.getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }

    private List<Order> createOrders(SubscriptionCreateEditDto object) {
        var orderIds = object.getOrders().stream()
            .map(orderService::create)
            .map(OrderReadDto::getId)
            .toList();

        return orderRepository.findAllById(orderIds);
    }
}

package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateAllFieldsDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreateMapper implements Mapper<SubscriptionCreateAllFieldsDto, Subscription> {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public Subscription map(SubscriptionCreateAllFieldsDto object) {
        var subscription = new Subscription();
        copy(object, subscription);

        return subscription;
    }

    private void copy(SubscriptionCreateAllFieldsDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);
        var orders = createOrder(object);

        subscription.setItem(item);
        subscription.setUser(user);
        subscription.setOrders(orders);
        subscription.setStatus(object.getStatus());
        subscription.setStartTime(object.getStartTime());
        subscription.setEndTime(object.getEndTime());
    }

    private User getUser(SubscriptionCreateAllFieldsDto object) {
        return userRepository.findById(object.getSubscriptionCreateEditDto().getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionCreateAllFieldsDto object) {
        return itemRepository.findById(object.getSubscriptionCreateEditDto().getItemId())
            .orElseThrow();
    }

    private List<Order> createOrder(SubscriptionCreateAllFieldsDto object) {
        var orderReadDto = orderService.create(object.getSubscriptionCreateEditDto().getOrder());

        return orderRepository.findById(orderReadDto.getId()).stream()
            .toList();
    }
}

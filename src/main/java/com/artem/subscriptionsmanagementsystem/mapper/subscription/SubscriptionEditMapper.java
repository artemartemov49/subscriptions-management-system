package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionEditMapper implements Mapper<SubscriptionCreateEditDto, Subscription> {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public Subscription map(SubscriptionCreateEditDto object) {
        return null;
    }

    private void copy(SubscriptionCreateEditDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);

        subscription.setItem(item);
        subscription.setUser(user);
//        subscription.setOrders();
    }

    private User getUser(SubscriptionCreateEditDto object) {
        return userRepository.findById(object.getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }

    private List<Order> getOrders(SubscriptionCreateEditDto object) {
        object.getOrders().stream()
            .map(or->or.getSubscriptionId())

        return orderRepository.findal()
            .orElseThrow();
    }
}

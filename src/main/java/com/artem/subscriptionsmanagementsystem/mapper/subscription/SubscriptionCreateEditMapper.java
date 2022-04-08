package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreateEditMapper implements Mapper<SubscriptionCreateEditDto, Subscription> {

    private final ItemRepository itemRepository;

    @Override
    public Subscription map(SubscriptionCreateEditDto object) {
        return null;
    }

    private void copy(SubscriptionCreateEditDto object, Subscription subscription) {
        Item item = getItem(object);

        subscription.setItem(item);
//        subscription.setOrders();
    }

    private Item getItem(SubscriptionCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }
}

package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.EditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionEditMapper implements EditMapper<SubscriptionEditDto, Subscription> {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Subscription map(SubscriptionEditDto fromObject, Subscription toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(SubscriptionEditDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);

        subscription.setItem(item);
        subscription.setUser(user);
        subscription.setStatus(object.getStatus());
        subscription.setStartTime(object.getStartTime());
        subscription.setEndTime(object.getEndTime());
    }

    private User getUser(SubscriptionEditDto object) {
        return userRepository.findById(object.getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }
}

package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.DISABLED;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreateEditMapper implements Mapper<SubscriptionCreateEditDto, Subscription> {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Subscription map(SubscriptionCreateEditDto object) {
        var subscription = new Subscription();
        copy(object, subscription);
        setTime(subscription);

        return subscription;
    }

    private void copy(SubscriptionCreateEditDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);

        subscription.setItem(item);
        subscription.setUser(user);
    }

    private void setTime(Subscription subscription) {
        subscription.setStartTime(LocalDate.now().minusMonths(2));
        subscription.setEndTime(LocalDate.now().minusMonths(1));
        subscription.setStatus(DISABLED);
    }

    private User getUser(SubscriptionCreateEditDto object) {
        return userRepository.findById(object.getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }
}

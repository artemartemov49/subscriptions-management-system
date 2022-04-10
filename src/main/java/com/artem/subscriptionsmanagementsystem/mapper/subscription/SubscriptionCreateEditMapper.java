package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.ACTIVE;
import static com.artem.subscriptionsmanagementsystem.database.entity.Status.DISABLED;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.duration.DurationReadMapper;
import com.artem.subscriptionsmanagementsystem.service.DurationService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreateEditMapper implements Mapper<SubscriptionCreateEditDto, Subscription> {

    private final PriceRepository priceRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private final DurationService durationService;

    private final DurationReadMapper durationReadMapper;

    @Override
    public Subscription map(SubscriptionCreateEditDto object) {
        var subscription = new Subscription();
        copy(object, subscription);
        setTimeFromNow(subscription, getMonths(object));

        return subscription;
    }

    @Override
    public Subscription map(SubscriptionCreateEditDto fromObject, Subscription toObject) {
        copy(fromObject, toObject);
        editDuration(fromObject, toObject);

        return toObject;
    }

    private void copy(SubscriptionCreateEditDto object, Subscription subscription) {
        var item = getItem(object);
        var user = getUser(object);

        subscription.setItem(item);
        subscription.setUser(user);
    }

    private void editDuration(SubscriptionCreateEditDto object, Subscription subscription) {
        var months = getMonths(object);
        var endTime = subscription.getEndTime();

        if (endTime.isAfter(LocalDate.now())) {
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

    private User getUser(SubscriptionCreateEditDto object) {
        return userRepository.findById(object.getUserId())
            .orElseThrow();
    }

    private Item getItem(SubscriptionCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }

    private Integer getMonths(SubscriptionCreateEditDto subscriptionDto) {
        var priceId = subscriptionDto.getPriceId();

        return priceRepository.findById(priceId)
            .map(Price::getDuration)
            .map(durationReadMapper::map)
            .map(durationService::getMonths)
            .orElseThrow();
    }
}

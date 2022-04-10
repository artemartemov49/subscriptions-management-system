package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionAddOrdersDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionAddOrdersMapper implements Mapper<SubscriptionAddOrdersDto, Subscription> {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription map(SubscriptionAddOrdersDto object) {
        var subscription = getSubscription(object);

        return null;
    }

    private Subscription getSubscription(SubscriptionAddOrdersDto object) {
        return subscriptionRepository.findById(object.getId())
            .orElseThrow();
    }
}

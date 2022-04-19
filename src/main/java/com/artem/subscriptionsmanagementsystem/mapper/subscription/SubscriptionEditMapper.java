package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.EditMapper;
import com.artem.subscriptionsmanagementsystem.util.SubscriptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionEditMapper implements EditMapper<SubscriptionEditDto, Subscription> {

    @Override
    public Subscription map(SubscriptionEditDto fromObject, Subscription toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(SubscriptionEditDto object, Subscription subscription) {
        subscription.setStartTime(object.getStartTime());
        subscription.setEndTime(object.getEndTime());

        SubscriptionUtil.setStatus(subscription);
    }

}

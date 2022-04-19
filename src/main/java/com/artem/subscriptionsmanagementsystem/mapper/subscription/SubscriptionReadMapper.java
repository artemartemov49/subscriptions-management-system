package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import com.artem.subscriptionsmanagementsystem.util.SubscriptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionReadMapper implements Mapper<Subscription, SubscriptionReadDto> {

    private final ItemReadMapper itemReadMapper;

    @Override
    public SubscriptionReadDto map(Subscription object) {
        var item = itemReadMapper.map(object.getItem());
        SubscriptionUtil.setStatus(object);
        String period = SubscriptionUtil.getPeriod(object);

        return new SubscriptionReadDto(
            object.getId(),
            item,
            object.getStartTime(),
            object.getEndTime(),
            period,
            object.getStatus()
        );
    }
}

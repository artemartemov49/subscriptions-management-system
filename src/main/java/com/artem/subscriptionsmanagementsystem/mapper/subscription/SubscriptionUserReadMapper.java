package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionUserReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.user.UserReadMapper;
import com.artem.subscriptionsmanagementsystem.util.SubscriptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionUserReadMapper implements Mapper<Subscription, SubscriptionUserReadDto> {

    private final UserReadMapper userReadMapper;
    private final ItemReadMapper itemReadMapper;

    @Override
    public SubscriptionUserReadDto map(Subscription object) {
        var item = itemReadMapper.map(object.getItem());
        var user = userReadMapper.map(object.getUser());

        SubscriptionUtil.setStatus(object);
        String period = SubscriptionUtil.getPeriod(object);

        return new SubscriptionUserReadDto(
            object.getId(),
            user,
            item,
            object.getStartTime(),
            object.getEndTime(),
            period,
            object.getStatus()
        );
    }
}

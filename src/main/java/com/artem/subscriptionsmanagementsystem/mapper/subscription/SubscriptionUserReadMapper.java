package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionUserReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.user.UserReadMapper;
import com.artem.subscriptionsmanagementsystem.util.DateUtil;
import java.time.LocalDate;
import java.util.List;
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

        setStatus(object);
        String period = getPeriod(object);

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

    private String getPeriod(Subscription object) {
        String period = "ended";

        if (object.getStatus().equals(Status.ACTIVE)) {
            period = DateUtil.stringifyPeriodFromNow(object.getEndTime());
        }

        return period;
    }

    private void setStatus(Subscription object) {
        if (object.getEndTime().isBefore(LocalDate.now())) {
            object.setStatus(Status.DISABLED);
        }
    }
}

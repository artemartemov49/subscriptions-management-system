package com.artem.subscriptionsmanagementsystem.mapper.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderReadMapper;
import com.artem.subscriptionsmanagementsystem.util.DateUtil;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.DateUtils;

@Component
@RequiredArgsConstructor
public class SubscriptionReadMapper implements Mapper<Subscription, SubscriptionReadDto> {

    private final ItemReadMapper itemReadMapper;
    private final OrderReadMapper orderReadMapper;

    @Override
    public SubscriptionReadDto map(Subscription object) {
        var item = itemReadMapper.map(object.getItem());
        List<OrderReadDto> orders = object.getOrders().stream()
            .map(orderReadMapper::map)
            .toList();
        var period = DateUtil.stringifyPeriod(object.getStartTime(), object.getEndTime());

        return new SubscriptionReadDto(
            object.getId(),
            item,
            object.getStartTime(),
            object.getEndTime(),
            period,
            object.getStatus(),
            orders
        );
    }
}

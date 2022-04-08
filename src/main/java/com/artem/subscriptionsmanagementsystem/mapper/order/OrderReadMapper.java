package com.artem.subscriptionsmanagementsystem.mapper.order;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.price.PriceReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final PriceReadMapper priceReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        return new OrderReadDto(object.getId(), priceReadMapper.map(object.getPrice()));
    }
}

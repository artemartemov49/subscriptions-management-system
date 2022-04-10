package com.artem.subscriptionsmanagementsystem.mapper.order;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.price.PriceReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreateMapper implements Mapper<OrderCreateDto, Order> {

    private final PriceReadMapper priceReadMapper;

    @Override
    public Order map(OrderCreateDto object) {
        return null;
    }

    private void copy(OrderCreateDto object, Order order){

    }
}

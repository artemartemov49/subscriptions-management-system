package com.artem.subscriptionsmanagementsystem.mapper.order;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.price.PriceReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreateMapper implements Mapper<OrderCreateEditDto, Order> {

    private final PriceReadMapper priceReadMapper;

    @Override
    public Order map(OrderCreateEditDto object) {
        return null;
    }

    private void copy(OrderCreateEditDto object, Order order){

    }
}

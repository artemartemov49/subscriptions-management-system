package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderCreateMapper;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderReadMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreateMapper orderCreateMapper;
    private final OrderReadMapper orderReadMapper;

    public OrderReadDto create(OrderCreateDto orderDto) {
        return Optional.of(orderDto)
            .map(orderCreateMapper::map)
            .map(orderRepository::save)
            .map(orderReadMapper::map)
            .orElseThrow();
    }
}

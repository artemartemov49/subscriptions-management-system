package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.OrderRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.order.SubscriptionWithOrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderCreateMapper;
import com.artem.subscriptionsmanagementsystem.mapper.order.OrderReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper;
    private final OrderCreateMapper orderCreateMapper;

    public List<OrderReadDto> findAll() {
        return orderRepository.findAll().stream()
            .map(orderReadMapper::map)
            .toList();
    }

    public Optional<OrderReadDto> findById(Integer id) {
        return orderRepository.findById(id)
            .map(orderReadMapper::map);
    }

    public OrderReadDto create(OrderCreateDto orderDto) {
        return Optional.of(orderDto)
            .map(orderCreateMapper::map)
            .map(orderRepository::saveAndFlush)
            .map(orderReadMapper::map)
            .orElseThrow();
    }
//
//    public OrderReadDto createWithNewSubscription(SubscriptionWithOrderCreateDto subscriptionAndOrder) {
//        SubscriptionCreateDto subscriptionDto = new SubscriptionCreateDto(subscriptionAndOrder.getUserId(), subscriptionAndOrder.getItemId());
//        var subscriptionReadDto = subscriptionService.create(subscriptionDto);
//
//        var orderCreateDto = new OrderCreateDto(subscriptionReadDto.getId(), subscriptionAndOrder.getPriceId());
//        return create(orderCreateDto);
//    }

    @Transactional
    public boolean delete(Integer id) {
        return orderRepository.findById(id)
            .map(entity -> {
                orderRepository.delete(entity);
                orderRepository.flush();
                return true;
            })
            .orElse(false);
    }
}

package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionCreateEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final OrderService orderService;

    private final SubscriptionCreateEditMapper subscriptionCreateEditMapper;
    private final SubscriptionReadMapper subscriptionReadMapper;

    public List<SubscriptionReadDto> createSubscriptions(List<SubscriptionCreateEditDto> subscriptionDto) {
        return subscriptionDto.stream()
            .map(this::create)
            .toList();
    }

    public SubscriptionReadDto create(SubscriptionCreateEditDto subscriptionDto) {
        var subscriptionReadDto = Optional.of(subscriptionDto)
            .map(subscriptionCreateEditMapper::map)
            .map(subscriptionRepository::save)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
        createOrder(subscriptionDto, subscriptionReadDto);

        return subscriptionReadDto;
    }

    @Transactional
    public SubscriptionReadDto update(Integer id, SubscriptionCreateEditDto subscriptionDto) {
        return subscriptionRepository.findById(id)
            .map(entity -> subscriptionCreateEditMapper.map(subscriptionDto, entity))
            .map(subscriptionRepository::saveAndFlush)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
    }

    private void createOrder(SubscriptionCreateEditDto subscriptionDto, SubscriptionReadDto subscriptionReadDto) {
        var orderDto = new OrderCreateDto(subscriptionReadDto.getId(), subscriptionDto.getPriceId());
        var orderReadDto = orderService.create(orderDto);

        subscriptionReadDto.getOrders().add(orderReadDto);
    }
}

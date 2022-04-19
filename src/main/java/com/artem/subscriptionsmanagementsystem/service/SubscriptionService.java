package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionUserReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionCreateMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionUserReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final OrderService orderService;
    private final SubscriptionRepository subscriptionRepository;
    private final PriceRepository priceRepository;
    private final SubscriptionReadMapper subscriptionReadMapper;
    private final SubscriptionUserReadMapper subscriptionUserReadMapper;
    private final SubscriptionCreateMapper subscriptionCreateMapper;
    private final SubscriptionEditMapper subscriptionEditMapper;

    public List<SubscriptionReadDto> findAll() {
        return subscriptionRepository.findAll().stream()
            .map(subscriptionReadMapper::map)
            .toList();
    }

    public List<SubscriptionUserReadDto> findAllWithUser() {
        return subscriptionRepository.findAll().stream()
            .map(subscriptionUserReadMapper::map)
            .toList();
    }

    public Optional<SubscriptionReadDto> findById(Integer id) {
        return subscriptionRepository.findById(id)
            .map(subscriptionReadMapper::map);
    }

    public Optional<SubscriptionReadDto> findByUserIdAndItemId(Integer userId, Integer itemId) {
        return subscriptionRepository.findByUserIdAndItemId(userId, itemId)
            .map(subscriptionReadMapper::map);
    }

    public Optional<SubscriptionUserReadDto> findByIdWithUser(Integer id) {
        return subscriptionRepository.findById(id)
            .map(subscriptionUserReadMapper::map);
    }

    @Transactional
    public SubscriptionReadDto create(SubscriptionCreateDto subscriptionDto) {
        return Optional.of(subscriptionDto)
            .map(subscriptionCreateMapper::map)
            .map(subscriptionRepository::save)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public SubscriptionReadDto addSubscription(SubscriptionCreateDto subscriptionDto) {
        var itemId = priceRepository.findById(subscriptionDto.getPriceId())
            .orElseThrow()
            .getItem().getId();

        var subscription = findByUserIdAndItemId(subscriptionDto.getUserId(), itemId);
        var subscriptionId = createSubscriptionIfNotExist(subscriptionDto, subscription);

        var orderCreateDto = new OrderCreateDto(subscriptionId, subscriptionDto.getPriceId());
        orderService.create(orderCreateDto);

        subscriptionRepository.flush();

        return findById(subscriptionId)
            .orElseThrow();
    }

    @Transactional
    public Optional<SubscriptionReadDto> update(Integer id, SubscriptionEditDto subscriptionDto) {
        return subscriptionRepository.findById(id)
            .map(entity -> subscriptionEditMapper.map(subscriptionDto, entity))
            .map(subscriptionRepository::saveAndFlush)
            .map(subscriptionReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return subscriptionRepository.findById(id)
            .map(entity -> {
                subscriptionRepository.delete(entity);
                subscriptionRepository.flush();
                return true;
            })
            .orElse(false);
    }

    private int createSubscriptionIfNotExist(SubscriptionCreateDto subscriptionAndOrder,
                                             Optional<SubscriptionReadDto> maybeSubscription) {
        var subscriptionDto = new SubscriptionCreateDto(subscriptionAndOrder.getUserId(), subscriptionAndOrder.getPriceId());

        return maybeSubscription.isEmpty()
            ? create(subscriptionDto).getId()
            : maybeSubscription.get().getId();
    }
}

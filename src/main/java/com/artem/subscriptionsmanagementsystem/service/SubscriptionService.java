package com.artem.subscriptionsmanagementsystem.service;

import static com.artem.subscriptionsmanagementsystem.database.entity.Status.ACTIVE;

import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionAddOrderDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionAddOrdersAllFieldsDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateAllFieldsDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.duration.DurationReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionAddOrdersMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionCreateMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionReadMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final PriceRepository priceRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final DurationService durationService;

    private final SubscriptionAddOrdersMapper subscriptionAddOrdersMapper;
    private final SubscriptionCreateMapper subscriptionCreateMapper;
    private final SubscriptionReadMapper subscriptionReadMapper;
    private final DurationReadMapper durationReadMapper;

    public List<SubscriptionReadDto> createSubscriptions(List<SubscriptionCreateEditDto> subscriptionDto) {
        return subscriptionDto.stream()
            .map(this::create)
            .toList();
    }

    public SubscriptionReadDto create(SubscriptionCreateEditDto subscriptionDto) {
        return Optional.of(subscriptionDto)
            .map(this::createAllFieldsDto)
            .map(subscriptionCreateMapper::map)
            .map(subscriptionRepository::save)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
    }

    public SubscriptionReadDto addOrders(SubscriptionAddOrderDto subscriptionDto) {
        return Optional.of(subscriptionDto)
            .map(this::addOrdersAllFieldsDto)
            .map(subscriptionAddOrdersMapper::map)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
    }

    private SubscriptionAddOrdersAllFieldsDto addOrdersAllFieldsDto(SubscriptionAddOrderDto subscriptionDto) {
        var subscriptionAllFieldsDto = new SubscriptionAddOrdersAllFieldsDto(subscriptionDto);

        var endTime = getEndTime(subscriptionDto);
        var months = getMonths(subscriptionDto.getOrder());
        setTime(endTime, subscriptionAllFieldsDto, months);

        return subscriptionAllFieldsDto;
    }

    private SubscriptionCreateAllFieldsDto createAllFieldsDto(SubscriptionCreateEditDto subscriptionDto) {
        Integer months = getMonths(subscriptionDto.getOrder());

        return new SubscriptionCreateAllFieldsDto(subscriptionDto,
            ACTIVE,
            LocalDate.now(),
            LocalDate.now().plusMonths(months)
        );
    }

    private LocalDate getEndTime(SubscriptionAddOrderDto subscriptionDto) {
        var id = subscriptionDto.getId();
        return subscriptionRepository.findById(id)
            .map(Subscription::getEndTime)
            .orElseThrow();
    }

    private Integer getMonths(OrderCreateDto orderDto) {
        var priceId = orderDto.getPriceId();

        return priceRepository.findById(priceId)
            .map(Price::getDuration)
            .map(durationReadMapper::map)
            .map(durationService::getMonths)
            .orElseThrow();
    }

    private void setTime(LocalDate endTime, SubscriptionAddOrdersAllFieldsDto subscriptionDto, Integer months) {
        if (endTime.isAfter(LocalDate.now())) {
            subscriptionDto.setStartTime(LocalDate.now());
            subscriptionDto.setEndTime(LocalDate.now().plusMonths(months));
            subscriptionDto.setStatus(ACTIVE);
        } else {
            subscriptionDto.setEndTime(endTime.plusMonths(months));
        }
    }
}

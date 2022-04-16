package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionCreateMapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionEditMapper;
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
    private final SubscriptionReadMapper subscriptionReadMapper;
    private final SubscriptionCreateMapper subscriptionCreateMapper;
    private final SubscriptionEditMapper subscriptionEditMapper;

    public List<SubscriptionReadDto> findAll() {
        return subscriptionRepository.findAll().stream()
            .map(subscriptionReadMapper::map)
            .toList();
    }

    public Optional<SubscriptionReadDto> findById(Integer id) {
        return subscriptionRepository.findById(id)
            .map(subscriptionReadMapper::map);
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
}

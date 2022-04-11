package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.SubscriptionRepository;
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
    private final SubscriptionReadMapper subscriptionReadMapper;
    private final SubscriptionCreateEditMapper subscriptionCreateEditMapper;

    public List<SubscriptionReadDto> findAll() {
        return subscriptionRepository.findAll().stream()
            .map(subscriptionReadMapper::map)
            .toList();
    }

    public Optional<SubscriptionReadDto> findById(Integer id) {
        return subscriptionRepository.findById(id)
            .map(subscriptionReadMapper::map);
    }

    public SubscriptionReadDto create(SubscriptionCreateEditDto subscriptionDto) {
        return Optional.of(subscriptionDto)
            .map(subscriptionCreateEditMapper::map)
            .map(subscriptionRepository::save)
            .map(subscriptionReadMapper::map)
            .orElseThrow();
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

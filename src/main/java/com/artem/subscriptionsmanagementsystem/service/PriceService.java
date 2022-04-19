package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.PriceRepository;
import com.artem.subscriptionsmanagementsystem.dto.price.PriceCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.price.PriceReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.price.PriceCreateEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.price.PriceReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceReadMapper priceReadMapper;
    private final PriceCreateEditMapper priceCreateEditMapper;

    public List<PriceReadDto> findAll() {
        return priceRepository.findAll().stream()
            .map(priceReadMapper::map)
            .toList();
    }

    public List<PriceReadDto> findAllByItemId(Integer itemId) {
        return priceRepository.findAllByItemId(itemId).stream()
            .map(priceReadMapper::map)
            .toList();
    }

    public Optional<PriceReadDto> findById(Integer id) {
        return priceRepository.findById(id)
            .map(priceReadMapper::map);
    }

    @Transactional
    public PriceReadDto create(PriceCreateEditDto priceDto) {
        return Optional.of(priceDto)
            .map(priceCreateEditMapper::map)
            .map(priceRepository::save)
            .map(priceReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<PriceReadDto> update(Integer id, PriceCreateEditDto userDto) {
        return priceRepository.findById(id)
            .map(entity -> priceCreateEditMapper.map(userDto, entity))
            .map(priceRepository::saveAndFlush)
            .map(priceReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return priceRepository.findById(id)
            .map(entity -> {
                priceRepository.delete(entity);
                priceRepository.flush();
                return true;
            })
            .orElse(false);
    }
}

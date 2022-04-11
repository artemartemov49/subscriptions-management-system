package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.DurationRepository;
import com.artem.subscriptionsmanagementsystem.dto.duration.DurationCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.duration.DurationReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.duration.DurationCreateEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.duration.DurationReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DurationService {

    private final DurationRepository durationRepository;
    private final DurationReadMapper durationReadMapper;
    private final DurationCreateEditMapper durationCreateEditMapper;

    public List<DurationReadDto> findAll() {
        return durationRepository.findAll().stream()
            .map(durationReadMapper::map)
            .toList();
    }

    public Optional<DurationReadDto> findById(Integer id) {
        return durationRepository.findById(id)
            .map(durationReadMapper::map);
    }

    public DurationReadDto create(DurationCreateEditDto durationDto) {
        return Optional.of(durationDto)
            .map(durationCreateEditMapper::map)
            .map(durationRepository::save)
            .map(durationReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<DurationReadDto> update(Integer id, DurationCreateEditDto durationDto) {
        return durationRepository.findById(id)
            .map(entity -> durationCreateEditMapper.map(durationDto, entity))
            .map(durationRepository::saveAndFlush)
            .map(durationReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return durationRepository.findById(id)
            .map(entity -> {
                durationRepository.delete(entity);
                durationRepository.flush();
                return true;
            })
            .orElse(false);
    }

}

package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemCreateEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemReadMapper itemReadMapper;
    private final ItemCreateEditMapper itemCreateEditMapper;

    public List<ItemReadDto> findAll() {
        return itemRepository.findAll().stream()
            .map(itemReadMapper::map)
            .toList();
    }

    public Optional<ItemReadDto> findById(Integer id) {
        return itemRepository.findById(id)
            .map(itemReadMapper::map);
    }

    @Transactional
    public ItemReadDto create(ItemCreateEditDto userDto) {
        return Optional.of(userDto)
            .map(itemCreateEditMapper::map)
            .map(itemRepository::save)
            .map(itemReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<ItemReadDto> update(Integer id, ItemCreateEditDto userDto) {
        return itemRepository.findById(id)
            .map(entity -> itemCreateEditMapper.map(userDto, entity))
            .map(itemRepository::saveAndFlush)
            .map(itemReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return itemRepository.findById(id)
            .map(entity -> {
                itemRepository.delete(entity);
                itemRepository.flush();
                return true;
            })
            .orElse(false);
    }
}

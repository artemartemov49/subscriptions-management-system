package com.artem.subscriptionsmanagementsystem.mapper.price;

import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.dto.price.PriceReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.duration.DurationReadMapper;
import com.artem.subscriptionsmanagementsystem.mapper.item.ItemReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceReadMapper implements Mapper<Price, PriceReadDto> {

    private final DurationReadMapper durationReadMapper;
    private final ItemReadMapper itemReadMapper;

    @Override
    public PriceReadDto map(Price object) {
        return new PriceReadDto(object.getId(),
            object.getAmount(),
            itemReadMapper.map(object.getItem()),
            durationReadMapper.map(object.getDuration()));
    }
}

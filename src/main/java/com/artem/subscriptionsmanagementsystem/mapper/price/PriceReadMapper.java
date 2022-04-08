package com.artem.subscriptionsmanagementsystem.mapper.price;

import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.dto.price.PriceReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.periods.PeriodsReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceReadMapper implements Mapper<Price, PriceReadDto> {

    private final PeriodsReadMapper periodsReadMapper;

    @Override
    public PriceReadDto map(Price object) {
        return new PriceReadDto(object.getId(), object.getAmount(), periodsReadMapper.map(object.getPeriods()));
    }
}

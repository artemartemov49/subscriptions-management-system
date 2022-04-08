package com.artem.subscriptionsmanagementsystem.mapper.periods;

import com.artem.subscriptionsmanagementsystem.database.entity.Periods;
import com.artem.subscriptionsmanagementsystem.dto.periods.PeriodsReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;

public class PeriodsReadMapper implements Mapper<Periods, PeriodsReadDto> {

    @Override
    public PeriodsReadDto map(Periods object) {
        return new PeriodsReadDto(object.getId(),
            object.getYears(),
            object.getMonths(),
            object.getDays()
        );
    }
}

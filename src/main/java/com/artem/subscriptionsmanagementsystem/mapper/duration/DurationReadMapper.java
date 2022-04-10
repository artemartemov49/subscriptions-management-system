package com.artem.subscriptionsmanagementsystem.mapper.duration;

import com.artem.subscriptionsmanagementsystem.database.entity.Duration;
import com.artem.subscriptionsmanagementsystem.dto.duration.DurationReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DurationReadMapper implements Mapper<Duration, DurationReadDto> {

    @Override
    public DurationReadDto map(Duration object) {
        return new DurationReadDto(object.getId(), object.getMonths());
    }
}

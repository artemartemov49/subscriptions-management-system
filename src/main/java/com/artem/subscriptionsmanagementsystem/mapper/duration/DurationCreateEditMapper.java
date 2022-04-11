package com.artem.subscriptionsmanagementsystem.mapper.duration;

import com.artem.subscriptionsmanagementsystem.database.entity.Duration;
import com.artem.subscriptionsmanagementsystem.dto.duration.DurationCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DurationCreateEditMapper implements Mapper<DurationCreateEditDto, Duration> {

    @Override
    public Duration map(DurationCreateEditDto object) {
        var duration = new Duration();
        copy(object, duration);

        return duration;
    }

    @Override
    public Duration map(DurationCreateEditDto fromObject, Duration toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(DurationCreateEditDto object, Duration duration) {

        duration.setMonths(object.getMonths());
    }

}

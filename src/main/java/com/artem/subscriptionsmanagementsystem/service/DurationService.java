package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.dto.duration.DurationReadDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DurationService {

    public Integer getMonths(List<DurationReadDto> orders) {
        return orders.stream()
            .map(this::getMonths)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public Integer getMonths(DurationReadDto order) {
        return order
            .getMonths();
    }

}

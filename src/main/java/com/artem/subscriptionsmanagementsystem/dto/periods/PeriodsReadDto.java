package com.artem.subscriptionsmanagementsystem.dto.periods;

import lombok.Value;

@Value
public class PeriodsReadDto {

    Integer id;

    Integer years;

    Integer month;

    Integer days;
}

package com.artem.subscriptionsmanagementsystem.dto.periods;

import lombok.Value;

@Value
public class PeriodsCreateEditDto {

    Integer years;
    Integer months;
    Integer days;
}

package com.artem.subscriptionsmanagementsystem.dto.price;

import com.artem.subscriptionsmanagementsystem.dto.periods.PeriodsReadDto;
import lombok.Value;

@Value
public class PriceReadDto {

    Integer id;

    Integer amount;

    PeriodsReadDto period;
}

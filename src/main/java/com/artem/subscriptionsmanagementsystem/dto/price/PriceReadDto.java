package com.artem.subscriptionsmanagementsystem.dto.price;

import com.artem.subscriptionsmanagementsystem.dto.period.PeriodReadDto;
import lombok.Value;

@Value
public class PriceReadDto {

    Integer id;

    Integer amount;

    PeriodReadDto period;
}

package com.artem.subscriptionsmanagementsystem.dto.price;

import com.artem.subscriptionsmanagementsystem.dto.duration.DurationReadDto;
import lombok.Value;

@Value
public class PriceReadDto {

    Integer id;

    Integer amount;

    DurationReadDto period;
}

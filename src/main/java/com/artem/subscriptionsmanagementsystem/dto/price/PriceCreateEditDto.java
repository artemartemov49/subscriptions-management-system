package com.artem.subscriptionsmanagementsystem.dto.price;

import lombok.Value;

@Value
public class PriceCreateEditDto {

    Integer amount;
    Integer itemId;
    Integer durationId;
}

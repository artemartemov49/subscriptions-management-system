package com.artem.subscriptionsmanagementsystem.dto.price;

import lombok.Value;

@Value
public class PriceCreateEditDto {

    Integer itemId;
    Integer amount;
    Integer periodId;
}

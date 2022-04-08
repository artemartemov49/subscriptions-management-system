package com.artem.subscriptionsmanagementsystem.dto.order;

import lombok.Value;

@Value
public class OrderCreateEditDto {

    Integer subscriptionId;
    Integer priceId;
}

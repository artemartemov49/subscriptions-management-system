package com.artem.subscriptionsmanagementsystem.dto.order;

import lombok.Value;

@Value
public class OrderCreateDto {

    Integer subscriptionId;
    Integer priceId;
}

package com.artem.subscriptionsmanagementsystem.dto.order;

import lombok.Value;

@Value
public class OrderCreateWithSubscriptionDto {

    Integer userId;
    Integer itemId;
    Integer priceId;
}

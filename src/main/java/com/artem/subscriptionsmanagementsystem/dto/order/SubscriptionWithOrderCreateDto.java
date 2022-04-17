package com.artem.subscriptionsmanagementsystem.dto.order;

import lombok.Value;

@Value
public class SubscriptionWithOrderCreateDto {

    Integer userId;
    Integer itemId;
    Integer priceId;
}

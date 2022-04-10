package com.artem.subscriptionsmanagementsystem.dto.subscription;

import lombok.Value;

@Value
public class SubscriptionCreateEditDto {

    Integer userId;
    Integer itemId;
    Integer priceId;
}

package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import lombok.Value;

@Value
public class SubscriptionCreateEditDto {

    Integer userId;
    Integer itemId;
    OrderCreateDto order;
}

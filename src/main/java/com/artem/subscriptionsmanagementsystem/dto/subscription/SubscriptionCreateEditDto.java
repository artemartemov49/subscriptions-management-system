package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateEditDto;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionCreateEditDto {

    Integer userId;
    Integer itemId;
    List<OrderCreateEditDto> orders;
}

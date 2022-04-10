package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.dto.order.OrderCreateDto;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionAddOrdersDto {

    Integer id;

    List<OrderCreateDto> orders;

}

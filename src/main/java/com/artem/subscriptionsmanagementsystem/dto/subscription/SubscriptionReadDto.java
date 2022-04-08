package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionReadDto {

    Integer id;

    ItemReadDto item;

    List<OrderReadDto> orders;

}

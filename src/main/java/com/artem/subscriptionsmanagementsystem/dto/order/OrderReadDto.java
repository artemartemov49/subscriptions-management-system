package com.artem.subscriptionsmanagementsystem.dto.order;

import com.artem.subscriptionsmanagementsystem.dto.price.PriceReadDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.dto.user.UserReadDto;
import lombok.Value;

@Value
public class OrderReadDto {

    Integer id;
    UserReadDto user;
    PriceReadDto price;
}

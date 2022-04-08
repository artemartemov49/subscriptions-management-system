package com.artem.subscriptionsmanagementsystem.dto.order;

import com.artem.subscriptionsmanagementsystem.dto.price.PriceReadDto;
import lombok.Value;

@Value
public class OrderReadDto {

    Integer id;
    PriceReadDto price;
}

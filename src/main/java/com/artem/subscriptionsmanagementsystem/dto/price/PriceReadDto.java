package com.artem.subscriptionsmanagementsystem.dto.price;

import com.artem.subscriptionsmanagementsystem.dto.duration.DurationReadDto;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import lombok.Value;

@Value
public class PriceReadDto {

    Integer id;

    Integer amount;

    ItemReadDto item;

    DurationReadDto duration;
}

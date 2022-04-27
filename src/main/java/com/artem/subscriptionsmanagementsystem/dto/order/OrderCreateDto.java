package com.artem.subscriptionsmanagementsystem.dto.order;

import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class OrderCreateDto {

    @NotNull
    Integer subscriptionId;

    @NotNull
    Integer priceId;
}

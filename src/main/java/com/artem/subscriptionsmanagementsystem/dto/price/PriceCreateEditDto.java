package com.artem.subscriptionsmanagementsystem.dto.price;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PriceCreateEditDto {

    @Min(0)
    @NotNull
    Integer amount;

    @NotNull
    Integer itemId;

    @NotNull
    Integer durationId;
}

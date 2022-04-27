package com.artem.subscriptionsmanagementsystem.dto.subscription;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Value
public class SubscriptionCreateDto {

    @NotNull
    Integer userId;

    @NotNull
    @DateTimeFormat
    Integer priceId;
}

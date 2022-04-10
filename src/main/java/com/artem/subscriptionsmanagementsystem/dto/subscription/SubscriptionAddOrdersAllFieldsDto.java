package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionAddOrdersAllFieldsDto {

    SubscriptionAddOrderDto subscriptionAddOrderDto;

    Status status;
    LocalDate startTime;
    LocalDate endTime;

    public SubscriptionAddOrdersAllFieldsDto(
        SubscriptionAddOrderDto subscriptionAddOrderDto) {
        this.subscriptionAddOrderDto = subscriptionAddOrderDto;
    }
}

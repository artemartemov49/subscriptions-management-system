package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionCreateAllFieldsDto {

    SubscriptionCreateEditDto subscriptionCreateEditDto;
    Status status;
    LocalDate startTime;
    LocalDate endTime;

    public SubscriptionCreateAllFieldsDto(
        SubscriptionCreateEditDto subscriptionCreateEditDto) {
        this.subscriptionCreateEditDto = subscriptionCreateEditDto;
    }
}

package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import java.time.LocalDate;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionReadDto {

    Integer id;

    ItemReadDto item;
    LocalDate startTime;
    LocalDate endTime;
    String period;
    Status status;

    List<OrderReadDto> orders;

}

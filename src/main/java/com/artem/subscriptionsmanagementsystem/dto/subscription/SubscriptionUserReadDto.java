package com.artem.subscriptionsmanagementsystem.dto.subscription;

import com.artem.subscriptionsmanagementsystem.database.entity.Status;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.dto.order.OrderReadDto;
import com.artem.subscriptionsmanagementsystem.dto.user.UserReadDto;
import java.time.LocalDate;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionUserReadDto {

    Integer id;

    UserReadDto user;
    ItemReadDto item;
    LocalDate startTime;
    LocalDate endTime;
    String period;
    Status status;

}

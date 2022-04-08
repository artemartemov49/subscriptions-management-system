package com.artem.subscriptionsmanagementsystem.dto.user;

import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import java.util.List;
import lombok.Value;

@Value
public class UserReadDto {

    Integer id;

    String name;

    String email;

    String phone;

    List<SubscriptionReadDto> subscriptions;
}

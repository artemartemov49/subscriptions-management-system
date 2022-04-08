package com.artem.subscriptionsmanagementsystem.dto.user;

import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateEditDto;
import java.util.List;
import lombok.Value;

@Value
public class UserCreateEditDto {

    String name;

    String email;

    String phone;

    List<SubscriptionCreateEditDto> subscriptions;
}

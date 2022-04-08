package com.artem.subscriptionsmanagementsystem.mapper.user;

import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionReadDto;
import com.artem.subscriptionsmanagementsystem.dto.user.UserReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionReadMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final SubscriptionReadMapper subscriptionReadMapper;

    @Override
    public UserReadDto map(User object) {
        List<SubscriptionReadDto> subscriptions = object.getSubscriptions().stream()
            .map(subscriptionReadMapper::map)
            .toList();

        return new UserReadDto(
            object.getId(),
            object.getName(),
            object.getEmail(),
            object.getPhone(),
            subscriptions
        );
    }
}

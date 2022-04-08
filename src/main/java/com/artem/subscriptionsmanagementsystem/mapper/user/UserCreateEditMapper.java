package com.artem.subscriptionsmanagementsystem.mapper.user;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.dto.user.UserCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import com.artem.subscriptionsmanagementsystem.mapper.subscription.SubscriptionCreateEditMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final SubscriptionCreateEditMapper subscriptionCreateEditMapper;

    @Override
    public User map(UserCreateEditDto object) {
        var user = new User();
        copy(object, user);

        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto object, User user) {
        List<Subscription> subscriptions = object.getSubscriptions().stream()
            .map(subscriptionCreateEditMapper::map)
            .toList();

        user.setName(object.getName());
        user.setEmail(object.getEmail());
        user.setPhone(object.getPhone());
        user.setSubscriptions(subscriptions);
    }
}

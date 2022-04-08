package com.artem.subscriptionsmanagementsystem.mapper;

import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.dto.user.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {

        return new UserReadDto(
            object.getId(),
            object.getName(),
            object.getEmail(),
            object.getPhone(),
            object.getSubscriptions()
        );
    }
}

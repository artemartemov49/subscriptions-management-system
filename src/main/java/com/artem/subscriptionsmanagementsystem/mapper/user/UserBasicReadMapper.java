package com.artem.subscriptionsmanagementsystem.mapper.user;

import com.artem.subscriptionsmanagementsystem.database.entity.User;
import com.artem.subscriptionsmanagementsystem.dto.user.UserBasicReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBasicReadMapper implements Mapper<User, UserBasicReadDto> {

    @Override
    public UserBasicReadDto map(User object) {

        return new UserBasicReadDto(
            object.getId(),
            object.getName(),
            object.getEmail(),
            object.getPhone()
        );
    }
}

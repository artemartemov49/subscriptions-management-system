package com.artem.subscriptionsmanagementsystem.dto.user;

import lombok.Value;

@Value
public class UserBasicReadDto {

    Integer id;

    String name;

    String email;

    String phone;
}

package com.artem.subscriptionsmanagementsystem.dto.user;

import lombok.Value;

@Value
public class UserCreateEditDto {

    String name;

    String email;

    String phone;
}

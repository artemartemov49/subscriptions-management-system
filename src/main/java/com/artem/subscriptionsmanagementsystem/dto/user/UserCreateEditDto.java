package com.artem.subscriptionsmanagementsystem.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class UserCreateEditDto {

    public static final String PHONE_REGEX = "^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?([-]?[\\s]?[0-9])+$";

    @NotBlank
    @Size(max = 16)
    String name;

    @Email()
    @NotBlank
    String email;

    @Pattern(
        regexp = PHONE_REGEX,
        message = "Phone doesn't match."
    )
    @NotBlank
    String phone;
}

package com.artem.subscriptionsmanagementsystem.dto.item;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ItemCreateEditDto {

    @NotBlank
    String name;
}

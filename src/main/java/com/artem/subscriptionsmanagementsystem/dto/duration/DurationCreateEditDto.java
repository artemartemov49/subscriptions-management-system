package com.artem.subscriptionsmanagementsystem.dto.duration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class DurationCreateEditDto {

    @Min(0)
    @Max(100)
    @NotNull
    Integer months;
}

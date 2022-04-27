package com.artem.subscriptionsmanagementsystem.dto.subscription;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Value
public class SubscriptionEditDto {

    @NotBlank
    @DateTimeFormat
    LocalDate startTime;

    @NotBlank
    @DateTimeFormat
    LocalDate endTime;
}

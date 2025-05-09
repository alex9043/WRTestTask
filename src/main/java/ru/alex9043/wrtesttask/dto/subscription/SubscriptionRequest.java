package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @JsonProperty("services_type")
    @NotBlank(message = "Service type не может быть пустым")
    private String servicesType;
}

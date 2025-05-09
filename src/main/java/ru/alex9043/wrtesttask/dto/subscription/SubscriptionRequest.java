package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @JsonProperty("services_type")
    private String servicesType;
}

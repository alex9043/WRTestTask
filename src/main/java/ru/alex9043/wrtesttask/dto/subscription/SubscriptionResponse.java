package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.alex9043.wrtesttask.model.enums.ServicesType;

import java.util.UUID;

public record SubscriptionResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("services_type") ServicesType servicesType,
        @JsonProperty("user_id") String userId) {
}

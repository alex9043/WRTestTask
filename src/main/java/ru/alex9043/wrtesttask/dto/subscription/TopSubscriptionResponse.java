package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.alex9043.wrtesttask.model.enums.ServicesType;

public record TopSubscriptionResponse(@JsonProperty("services_type") ServicesType servicesType) {
}

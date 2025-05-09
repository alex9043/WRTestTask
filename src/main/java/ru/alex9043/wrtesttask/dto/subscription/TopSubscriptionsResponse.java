package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record TopSubscriptionsResponse(@JsonProperty("top_subscriptions") Set<TopSubscriptionResponse> subscriptions) {
}

package ru.alex9043.wrtesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.model.Subscription;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SubscriptionMapper {
    Subscription toEntity(SubscriptionRequest subscriptionRequest);

    @Mapping(source = "user.id", target = "userId")
    SubscriptionResponse toResponse(Subscription subscription);
}

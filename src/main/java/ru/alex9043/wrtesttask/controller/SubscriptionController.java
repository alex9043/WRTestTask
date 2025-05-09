package ru.alex9043.wrtesttask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<SubscriptionResponse> createSubscription(
            @PathVariable("id") String userId,
            @RequestBody SubscriptionRequest subscriptionRequest) {
        log.info("Запрос на создание подписки");
        return subscriptionService.createSubscription(userId, subscriptionRequest);
    }
}

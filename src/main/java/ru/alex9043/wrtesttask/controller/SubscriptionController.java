package ru.alex9043.wrtesttask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.dto.subscription.UserSubscriptionsResponse;
import ru.alex9043.wrtesttask.service.SubscriptionService;

import java.util.UUID;

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

    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<UserSubscriptionsResponse> getUserSubscriptions(@PathVariable("id") String userId) {
        log.info("Запрос на получение списка подписок пользвоателя");
        return subscriptionService.getUserSubscriptions(userId);
    }

    @DeleteMapping("/users/{user_id}/subscriptions/{sub_id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable("user_id") String userId,
                                                     @PathVariable("sub_id") UUID subId) {
        log.info("Запрос на удаление подписки у пользвоателя");
        return subscriptionService.deleteSubscription(userId, subId);
    }
}

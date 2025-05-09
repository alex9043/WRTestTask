package ru.alex9043.wrtesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.dto.subscription.TopSubscriptionsResponse;
import ru.alex9043.wrtesttask.dto.subscription.UserSubscriptionsResponse;
import ru.alex9043.wrtesttask.service.SubscriptionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Subscription API", description = "Управление подписками пользователей")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Создать подписку",
            description = "Добавляет новую подписку для указанного пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Подписка создана", content = @Content(schema =
            @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "200", description = "Подписка уже существует", content = @Content),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Неверный тип сервиса")
    })
    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<SubscriptionResponse> createSubscription(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") String userId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания подписки",
                    content = @Content(schema = @Schema(implementation = SubscriptionRequest.class))
            )
            @Validated @RequestBody SubscriptionRequest subscriptionRequest) {
        return subscriptionService.createSubscription(userId, subscriptionRequest);
    }

    @Operation(
            summary = "Получить подписки пользователя",
            description = "Возвращает все подписки указанного пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(schema =
            @Schema(implementation = UserSubscriptionsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<UserSubscriptionsResponse> getUserSubscriptions(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") String userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    @Operation(
            summary = "Удалить подписку",
            description = "Удаляет конкретную подписку пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Подписка удалена"),
            @ApiResponse(responseCode = "404", description = "Пользователь или подписка не найдена")
    })
    @DeleteMapping("/users/{user_id}/subscriptions/{sub_id}")
    public ResponseEntity<String> deleteSubscription(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("user_id") String userId,

            @Parameter(description = "ID подписки", example = "d3d94468-0327-11ef-826c-0242ac140002")
            @PathVariable("sub_id") UUID subId) {
        return subscriptionService.deleteSubscription(userId, subId);
    }

    @Operation(
            summary = "Топ подписок",
            description = "Возвращает 3 самых популярных типа подписок"
    )
    @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(schema =
    @Schema(implementation = TopSubscriptionsResponse.class)))
    @GetMapping("/subscriptions/top")
    public ResponseEntity<TopSubscriptionsResponse> getTopSubscriptions() {
        return subscriptionService.getTopSubscriptions();
    }
}
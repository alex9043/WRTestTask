package ru.alex9043.wrtesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.mapper.SubscriptionMapper;
import ru.alex9043.wrtesttask.model.Subscription;
import ru.alex9043.wrtesttask.model.User;
import ru.alex9043.wrtesttask.repo.SubscriptionRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;

    @Transactional
    public ResponseEntity<SubscriptionResponse> createSubscription(String userId,
                                                                   SubscriptionRequest request) {
        log.debug("id пользователя - {}, тело запроса - {}", userId, request);
        User user = userService.findUserById(userId);

        request.setServicesType(request.getServicesType().toUpperCase());
        Subscription subscription = subscriptionMapper.toEntity(request);
        subscription.setUser(user);

        subscriptionRepository.save(subscription);
        SubscriptionResponse response = subscriptionMapper.toResponse(subscription);
        log.info("Подписка создана");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

package ru.alex9043.wrtesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionRequest;
import ru.alex9043.wrtesttask.dto.subscription.SubscriptionResponse;
import ru.alex9043.wrtesttask.dto.subscription.UserSubscriptionsResponse;
import ru.alex9043.wrtesttask.mapper.SubscriptionMapper;
import ru.alex9043.wrtesttask.model.Subscription;
import ru.alex9043.wrtesttask.model.User;
import ru.alex9043.wrtesttask.repo.SubscriptionRepository;

import java.util.Set;

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

        Subscription subscription = createSubscriptionEntity(user, request);

        return subscriptionRepository.findByUserAndServicesType(user, subscription.getServicesType())
                .map(this::existingSubscriptionResponse)
                .orElseGet(() -> createNewSubscription(subscription));
    }

    private ResponseEntity<SubscriptionResponse> existingSubscriptionResponse(Subscription subscription) {
        SubscriptionResponse response = subscriptionMapper.toResponse(subscription);
        log.info("Подписка уже существует у пользователя");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<SubscriptionResponse> createNewSubscription(Subscription subscription) {
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        SubscriptionResponse response = subscriptionMapper.toResponse(savedSubscription);
        log.info("Подписка создана");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    private Subscription createSubscriptionEntity(User user, SubscriptionRequest request) {
        request.setServicesType(request.getServicesType().toUpperCase());
        Subscription entity = subscriptionMapper.toEntity(request);
        entity.setUser(user);
        return entity;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserSubscriptionsResponse> getUserSubscriptions(String userId) {
        log.debug("id пользователя - {}", userId);
        User user = userService.findUserById(userId);

        Set<Subscription> subscriptions = subscriptionRepository.findAllByUser(user);
        Set<SubscriptionResponse> responseSet = subscriptionMapper.toResponseSet(subscriptions);

        log.info("Подписки пользователя получены");
        return new ResponseEntity<>(new UserSubscriptionsResponse(responseSet), HttpStatus.OK);
    }
}

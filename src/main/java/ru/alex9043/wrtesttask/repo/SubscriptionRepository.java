package ru.alex9043.wrtesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex9043.wrtesttask.model.Subscription;
import ru.alex9043.wrtesttask.model.User;
import ru.alex9043.wrtesttask.model.enums.ServicesType;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Set<Subscription> findAllByUser(User user);

    Optional<Subscription> findByUserAndServicesType(User user, ServicesType servicesType);

    void deleteByIdAndUser(UUID id, User user);
}
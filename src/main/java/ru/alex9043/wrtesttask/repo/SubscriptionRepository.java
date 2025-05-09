package ru.alex9043.wrtesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex9043.wrtesttask.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
}
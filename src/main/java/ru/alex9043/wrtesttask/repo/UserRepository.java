package ru.alex9043.wrtesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.alex9043.wrtesttask.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
}
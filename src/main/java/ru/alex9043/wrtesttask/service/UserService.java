package ru.alex9043.wrtesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex9043.wrtesttask.dto.user.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UpdateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UserResponse;
import ru.alex9043.wrtesttask.exception.UserNotFoundException;
import ru.alex9043.wrtesttask.mapper.UserMapper;
import ru.alex9043.wrtesttask.model.User;
import ru.alex9043.wrtesttask.repo.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        log.debug("Тело запроса - {}", createUserRequest);
        User user = userMapper.createToEntity(createUserRequest);
        User savedUser = userRepository.save(user);
        UserResponse response = userMapper.toResponse(savedUser);
        log.info("Пользователь создан");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserResponse> getUser(String id) {
        log.debug("Id получаемого пользователя - {}", id);
        User user = findUserById(id);
        UserResponse response = userMapper.toResponse(user);
        log.info("Пользователь получен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserResponse> updateUser(String id, UpdateUserRequest updateUserRequest) {
        log.debug("Id обновляемого пользователя - {}, тело запроса - {}", id, updateUserRequest);
        User user = findUserById(id);
        userMapper.updateUserFromRequest(updateUserRequest, user);
        User savedUser = userRepository.save(user);
        UserResponse response = userMapper.toResponse(savedUser);
        log.info("Пользователь обновлен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String id) {
        log.debug("Id удаляемого пользователя - {}", id);
        User user = findUserById(id);
        userRepository.delete(user);
        log.info("Пользователь удален");
        return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
    }

    @Transactional(readOnly = true)
    public User findUserById(String userId) {
        log.info("Попытка найти пользователя");
        return userRepository.findById(userId).orElseThrow(
                () -> {
                    log.error("Пользователь с id - {} не найден", userId);
                    return new UserNotFoundException("User not found by id: " + userId);
                }
        );
    }
}

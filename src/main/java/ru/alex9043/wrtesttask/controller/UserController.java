package ru.alex9043.wrtesttask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex9043.wrtesttask.dto.user.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UpdateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UserResponse;
import ru.alex9043.wrtesttask.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        log.info("Запрос на создание пользователя");
        return userService.createUser(createUserRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        log.info("Запрос на получение пользователя");
        return userService.getUser(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") String id,
            @RequestBody UpdateUserRequest updateUserRequest) {
        log.info("Запрос на изменение пользователя");
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        log.info("Запрос на удаление пользователя");
        return userService.deleteUser(id);
    }
}

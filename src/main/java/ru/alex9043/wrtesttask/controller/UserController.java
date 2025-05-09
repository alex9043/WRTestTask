package ru.alex9043.wrtesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex9043.wrtesttask.dto.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.UserResponse;
import ru.alex9043.wrtesttask.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }
}

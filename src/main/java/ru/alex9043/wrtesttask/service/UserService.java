package ru.alex9043.wrtesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.alex9043.wrtesttask.dto.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.UpdateUserRequest;
import ru.alex9043.wrtesttask.dto.UserResponse;
import ru.alex9043.wrtesttask.mapper.UserMapper;
import ru.alex9043.wrtesttask.model.User;
import ru.alex9043.wrtesttask.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.createToEntity(createUserRequest);
        User savedUser = userRepository.save(user);
        UserResponse response = userMapper.toResponse(savedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<UserResponse> getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found by id")
        );
        UserResponse response = userMapper.toResponse(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserResponse> updateUser(String id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found by id")
        );
        userMapper.updateUserFromRequest(updateUserRequest, user);
        User savedUser = userRepository.save(user);
        UserResponse response = userMapper.toResponse(savedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
    }
}

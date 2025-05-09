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
import ru.alex9043.wrtesttask.dto.user.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UpdateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UserResponse;
import ru.alex9043.wrtesttask.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User API", description = "Управление пользователями")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Создать пользователя",
            description = "Регистрирует нового пользователя в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пользователь создан", content =
            @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные пользователя",
                    content = @Content(schema = @Schema(implementation = CreateUserRequest.class)))
            @Validated @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @Operation(
            summary = "Получить пользователя",
            description = "Возвращает данные пользователя по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content =
            @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @Operation(
            summary = "Обновить пользователя",
            description = "Обновляет данные существующего пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные обновлены", content =
            @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") String id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новые данные пользователя",
                    content = @Content(schema = @Schema(implementation = UpdateUserRequest.class)))
            @Validated @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя и все его подписки"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пользователь удален")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") String id) {
        return userService.deleteUser(id);
    }
}
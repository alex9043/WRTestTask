package ru.alex9043.wrtesttask.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @JsonProperty("username")
        @Schema(description = "Имя пользователя", minLength = 3, maxLength = 50, example = "test2")
        @Size(min = 3, max = 50, message = "Username должен быть от 3 до 50 символов")
        String username,
        @JsonProperty("email")
        @Schema(description = "Email пользователя", format = "email", example = "test2@test.test")
        @Email(message = "Неправильный формат Email")
        String email
) {
}

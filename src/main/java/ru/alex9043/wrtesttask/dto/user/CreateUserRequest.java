package ru.alex9043.wrtesttask.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @JsonProperty("id")
        @Schema(description = "Id пользователя", example = "1")
        @NotBlank(message = "ID не может быть пустым")
        String id,
        @JsonProperty("username")
        @Schema(description = "Имя пользователя", minLength = 3, maxLength = 50, example = "test")
        @NotBlank(message = "Username не может быть пустым")
        @Size(min = 3, max = 50, message = "Username должен быть от 3 до 50 символов")
        String username,
        @JsonProperty("email")
        @Schema(description = "Email пользователя", format = "email", example = "test@test.test")
        @NotBlank(message = "Email не может быть пустым")
        @Email(message = "Неправильный формат Email")
        String email
) {
}

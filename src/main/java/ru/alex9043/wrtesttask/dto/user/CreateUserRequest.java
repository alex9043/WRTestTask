package ru.alex9043.wrtesttask.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @JsonProperty("id")
        @NotBlank(message = "ID не может быть пустым")
        String id,
        @JsonProperty("username")
        @NotBlank(message = "Username не может быть пустым")
        @Size(min = 3, max = 50, message = "Username должен быть от 3 до 50 символов")
        String username,
        @JsonProperty("email")
        @NotBlank(message = "Email не может быть пустым")
        @Email(message = "Неправильный формат Email")
        String email
) {
}

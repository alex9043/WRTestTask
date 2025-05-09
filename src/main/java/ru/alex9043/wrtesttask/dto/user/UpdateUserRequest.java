package ru.alex9043.wrtesttask.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @JsonProperty("username")
        @Size(min = 3, max = 50, message = "Username должен быть от 3 до 50 символов")
        String username,
        @JsonProperty("email")
        @Email(message = "Неправильный формат Email")
        String email
) {
}

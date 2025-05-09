package ru.alex9043.wrtesttask.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("username")
        String username,
        @JsonProperty("email")
        String email
) {
}

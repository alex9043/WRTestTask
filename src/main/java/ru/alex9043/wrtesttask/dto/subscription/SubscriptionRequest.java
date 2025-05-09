package ru.alex9043.wrtesttask.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @JsonProperty("services_type")
    @Schema(
            description = "Название сервиса",
            example = "YOUTUBE_PREMIUM",
            allowableValues = {"YOUTUBE_PREMIUM", "VK_MUSIC", "YANDEX_PLUS", "NETFLIX"}
    )
    @NotBlank(message = "Service type не может быть пустым")
    private String servicesType;
}

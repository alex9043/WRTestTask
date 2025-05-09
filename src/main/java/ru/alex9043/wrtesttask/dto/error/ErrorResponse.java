package ru.alex9043.wrtesttask.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(LocalDateTime timestamp, int status, String message, Map<String, String> errors) {
}

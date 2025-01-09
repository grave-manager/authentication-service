package com.dl.gm.authentication.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Standard response object for errors")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "404")
    private final int status;

    @Schema(description = "Error type", example = "Not Found")
    private final String error;

    @Schema(description = "Error message details", example = "Resource not found")
    private final String message;

    @Schema(description = "Path of the request that caused the error", example = "/api/users/123")
    private final String path;

    @Schema(description = "Timestamp of the error occurrence", example = "2024-12-04T12:00:00")
    private final LocalDateTime timestamp;

    private final List<String> details;

    public static ErrorResponse of(HttpStatus status, String error, String message, HttpServletRequest request,
                                   List<String> details) {
        return new ErrorResponse(
                status.value(),
                error,
                message,
                request.getRequestURI(),
                LocalDateTime.now(),
                details);
    }
}

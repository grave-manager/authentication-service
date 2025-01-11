package com.dl.gm.authentication.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {

    @Schema(name = "")
    private final int status;

    @Schema(name = "error name")
    private final String name;

    @Schema(name = "error key")
    private final String key;

    @Schema(name = "error message")
    private final String message;
}
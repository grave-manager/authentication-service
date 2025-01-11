package com.dl.gm.authentication.service.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
class AuthenticationRequest {

    @NotBlank
    @Schema(example = "kowalski99@gmail.com")
    private final String email;

    @NotBlank
    @Schema(example = "Password1$")
    private final String password;
}

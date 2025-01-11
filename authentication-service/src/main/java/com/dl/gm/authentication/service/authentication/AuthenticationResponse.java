package com.dl.gm.authentication.service.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
class AuthenticationResponse {

    private final String jwtToken;
}

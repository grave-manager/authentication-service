package com.dl.gm.authentication.service.authentication;

import com.dl.gm.authentication.service.jwt.JwtService;
import com.dl.gm.authentication.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    AuthenticationResponse signIn(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userService.getUser(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwt)
                .build();
    }
}

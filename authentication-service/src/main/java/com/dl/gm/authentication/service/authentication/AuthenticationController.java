package com.dl.gm.authentication.service.authentication;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth api", description = "Api that allows to sign in for registered in the system user")
@Slf4j
@RestController()
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:8080", methods = RequestMethod.POST)
class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("v1/auth/sign-in")
    AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("Authorization request received for user with email=#{}", authenticationRequest.getEmail());
        try {
            return authenticationService.signIn(authenticationRequest);
        } catch (Exception e) {
            log.info("Can't authorize user with email=#{}", authenticationRequest.getEmail());
            throw e;
        }
    }
}

package com.dl.gm.authentication.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    List<String> extractRoles(String token);
}

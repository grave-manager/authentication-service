package com.dl.gm.authentication.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    User getUser(String email);

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}

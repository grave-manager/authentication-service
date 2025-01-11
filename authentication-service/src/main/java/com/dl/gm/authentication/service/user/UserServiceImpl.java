package com.dl.gm.authentication.service.user;

import com.dl.gm.authentication.service.exception.EmailAlreadyExistException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    User createUser(User user) {
        validateIfUserNotAlreadyExists(user);
        var encryptedPassword = passwordEncoder.encode(user.getPassword());
        var entity = UserEntity.from(user, encryptedPassword);
        return User.from(userRepository.save(entity));
    }

    private void validateIfUserNotAlreadyExists(User user) {
        Optional<UserEntity> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            log.info("User with email: {} already exist", user.getEmail());
            throw new EmailAlreadyExistException(String.format("User with email: %s already exist", user.getEmail()));
        }
    }

    @Override
    public User getUser(String email) {
        var user = findByEmailOrThrow(email);
        return User.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = findByEmailOrThrow(email);
        return User.from(userEntity);
    }

    private UserEntity findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email=%s not found".formatted(email)));
    }

    public List<User> getAllUserWithRoles(List<UserRole> roles) {
        return userRepository.findAllByUserRoleIn(roles).stream()
                .map(User::from)
                .toList();
    }
}

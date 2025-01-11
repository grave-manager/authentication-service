package com.dl.gm.authentication.service.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UserDto {

    @Schema(example = "1")
    private final Long id;

    @Schema(example = "Jan")
    private final String firstName;

    @Schema(example = "Nowak")
    private final String lastName;

    @Schema(example = "nowak9@gmail.com")
    private final String email;

    static UserDto from(User user) {
        return UserMapper.INSTANCE.toUserDto(user);
    }
}

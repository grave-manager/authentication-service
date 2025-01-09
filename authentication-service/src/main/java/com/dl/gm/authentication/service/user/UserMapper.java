package com.dl.gm.authentication.service.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
abstract class UserMapper {

    static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    abstract User toUser(UserCreateRequestDto createRequestDto);

    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "password", source = "encryptedPassword")
    abstract UserEntity toEntity(User user, String encryptedPassword);

    @Mapping(target = "authorities", ignore = true)
    abstract User toUser(UserEntity userEntity);

    abstract UserDto toUserDto(User user);
}

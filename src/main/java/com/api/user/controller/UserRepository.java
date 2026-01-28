package com.api.user.controller;

import com.api.user.model.UserDto;

public interface UserRepository {

    UserDto.SignupResponse signup(UserDto.Request reqDto);
    UserDto.LoginResponse login(UserDto.Request reqDto);
}

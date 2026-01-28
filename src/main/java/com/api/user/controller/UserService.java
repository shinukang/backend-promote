package com.api.user.controller;

import com.api.user.model.UserDto;

public class UserService {

    public UserDto.SignupResponse signup(UserDto.Request reqDto) {
        UserRepository userRepository = new UserRepository();
        return userRepository.signup(reqDto);
    }

    public UserDto.LoginResponse login(UserDto.Request reqDto) {
        UserRepository userRepository = new UserRepository();
        return userRepository.login(reqDto);
    }
}

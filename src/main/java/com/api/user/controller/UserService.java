package com.api.user.controller;

import com.api.user.model.UserDto;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto.SignupResponse signup(UserDto.Request reqDto) {
        return userRepository.signup(reqDto);
    }

    public UserDto.LoginResponse login(UserDto.Request reqDto) {
        return userRepository.login(reqDto);
    }
}

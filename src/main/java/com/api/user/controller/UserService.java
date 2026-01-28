package com.api.user.controller;

import com.api.user.model.UserDto;

public class UserService {
    private UserService() {

    }

    private static class SingletonHolder {
        private static final UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    public UserDto.SignupResponse signup(UserDto.Request reqDto) {
        UserRepository userRepository = UserRepository.getInstance();
        return userRepository.signup(reqDto);
    }

    public UserDto.LoginResponse login(UserDto.Request reqDto) {
        UserRepository userRepository = UserRepository.getInstance();
        return userRepository.login(reqDto);
    }
}

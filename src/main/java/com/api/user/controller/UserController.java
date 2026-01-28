package com.api.user.controller;

import com.api.user.model.UserDto;
import com.common.BaseResponse;
import com.common.Controller;
import com.utils.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserController implements Controller {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse response) {
        UserDto.Request reqDto = JsonParser.from(req, UserDto.Request.class);
        BaseResponse res = BaseResponse.failure(null);
        if (req.getServletPath().contains("signup")) {
            res = BaseResponse.success(userService.signup(reqDto));
        } else if (req.getServletPath().contains("login")) {
            res = BaseResponse.success(userService.login(reqDto));
        }
        return res;
    }
}

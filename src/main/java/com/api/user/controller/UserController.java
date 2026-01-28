package com.api.user.controller;

import com.api.user.model.UserDto;
import com.common.BaseResponse;
import com.utils.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/user/signup", "/user/login"})
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto.Request reqDto = JsonParser.from(req, UserDto.Request.class);
        UserService userService = UserService.getInstance();
        BaseResponse res = BaseResponse.failure(null);
        if (req.getServletPath().contains("signup")) {
            res = BaseResponse.success(userService.signup(reqDto));
        } else if (req.getServletPath().contains("login")) {
            res = BaseResponse.success(userService.login(reqDto));
        }
        resp.getWriter().write(JsonParser.from(res));
    }
}

package com.api.user.controller;

import com.api.user.model.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.common.Config.*;
import static com.common.Config.dbPassword;
import static com.common.Config.dbUrl;
import static com.common.Config.dbUser;

@WebServlet(urlPatterns = {"/user/signup", "/user/login"})
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto.Request reqDto = UserDto.Request.create(req);
        UserService userService = new UserService();
        String res = "";
        if (req.getServletPath().contains("signup")) {
            res = userService.signup(reqDto).toString();
        } else if (req.getServletPath().contains("login")) {
            res = userService.login(reqDto).toString();
        }
        resp.getWriter().write(res);
    }
}

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
        UserDto userDto = UserDto.create(req);
        if (req.getServletPath().contains("signup")) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    final String sql = "INSERT INTO user(email, name, password) VALUES (?, ?, ?)";
                    try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                        pStatement.setString(1, userDto.getEmail());
                        pStatement.setString(2, userDto.getName());
                        pStatement.setString(3, userDto.getPassword());
                        pStatement.executeUpdate();
                        resp.getWriter().write("회원가입 완료");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (req.getServletPath().contains("login")) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    final String sql = "SELECT * FROM user WHERE email=? AND password=?";
                    try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                        pStatement.setString(1, userDto.getEmail());
                        pStatement.setString(2, userDto.getPassword());

                        ResultSet rs = pStatement.executeQuery();
                        if (rs.next()) {
                            resp.getWriter().write(rs.getString("name") + " 로그인 성공");
                        } else {
                            resp.getWriter().write("로그인 실패");
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

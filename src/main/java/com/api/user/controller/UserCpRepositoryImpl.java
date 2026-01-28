package com.api.user.controller;

import com.api.user.model.UserDto;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class UserCpRepositoryImpl implements UserRepository {
    private final HikariDataSource ds;

    public UserCpRepositoryImpl(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public UserDto.SignupResponse signup(UserDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = ds.getConnection()) {
                final String sql = "INSERT INTO user(email, name, password) VALUES (?, ?, ?)";
                try (PreparedStatement pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pStatement.setString(1, reqDto.getEmail());
                    pStatement.setString(2, reqDto.getName());
                    pStatement.setString(3, reqDto.getPassword());
                    pStatement.executeUpdate();
                    ResultSet rs = pStatement.getGeneratedKeys();
                    return UserDto.SignupResponse.create(rs.next() ? rs.getString("insert_id") : "-1");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto.LoginResponse login(UserDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = ds.getConnection()) {
                final String sql = "SELECT * FROM user WHERE email=? AND password=?";
                try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                    pStatement.setString(1, reqDto.getEmail());
                    pStatement.setString(2, reqDto.getPassword());

                    ResultSet rs = pStatement.executeQuery();
                    return UserDto.LoginResponse.create(rs.next() ? rs.getString("name") : "Unknown");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

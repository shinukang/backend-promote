package com.api.user.model;

import jakarta.servlet.http.HttpServletRequest;

public class UserDto {
    private String email;
    private String name;
    private String password;

    private UserDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public static UserDto create(HttpServletRequest req) {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        return new UserDto(email, name, password);
    }
}

package com.api.user.model;

import jakarta.servlet.http.HttpServletRequest;

public class UserDto {

    public static class Request {
        private String email;
        private String name;
        private String password;

        private Request(String email, String name, String password) {
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

        public static Request create(HttpServletRequest req) {
            String email = req.getParameter("email");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            return new Request(email, name, password);
        }
    }

    public static class SignupResponse {
        private String idx;

        private SignupResponse(String idx) {
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "SignupResponse{" +
                    "idx='" + idx + '\'' +
                    '}';
        }

        public static SignupResponse create(String idx) {
            return new SignupResponse(idx);
        }
    }

    public static class LoginResponse {
        private String name;

        private LoginResponse(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public static LoginResponse create(String name) {
            return new LoginResponse(name);
        }
    }
}

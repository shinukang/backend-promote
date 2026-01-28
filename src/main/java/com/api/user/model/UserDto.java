package com.api.user.model;

import jakarta.servlet.http.HttpServletRequest;

public class UserDto {

    public static class Request {
        private String email;
        private String name;
        private String password;

        public Request() {

        }

        private Request(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public  SignupResponse() {

        }
        private SignupResponse(String idx) {
            this.idx = idx;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public static SignupResponse create(String idx) {
            return new SignupResponse(idx);
        }
    }

    public static class LoginResponse {
        private String name;

        public LoginResponse() {

        }
        private LoginResponse(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static LoginResponse create(String name) {
            return new LoginResponse(name);
        }
    }
}

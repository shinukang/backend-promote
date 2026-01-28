package com.common;

public class Config {
    public static final String dbUrl = System.getenv("DB_URL");
    public static final String dbUser = System.getenv("DB_USERNAME");
    public static final String dbPassword = System.getenv("DB_PASSWORD");
}

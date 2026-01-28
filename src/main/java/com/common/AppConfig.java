package com.common;

import com.api.board.controller.*;
import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final HikariDataSource ds = new HikariDataSource();
    private final BoardRepository boardRepository = new BoardCpRepositoryImpl(ds);
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    public AppConfig() {
        ds.setJdbcUrl(System.getenv("DB_URL"));
        ds.setUsername(System.getenv("DB_USERNAME"));
        ds.setPassword(System.getenv("DB_PASSWORD"));

        controllerMap.put("/board/write", boardController);
        controllerMap.put("/board/read", boardController);
    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}

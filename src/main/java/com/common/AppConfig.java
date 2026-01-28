package com.common;

import com.api.board.controller.BoardController;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    private final BoardController boardController = new BoardController();

    public AppConfig() {
        controllerMap.put("/board/write", boardController);
        controllerMap.put("/board/read", boardController);
    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}

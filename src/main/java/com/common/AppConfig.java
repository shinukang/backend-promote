package com.common;

import com.api.board.controller.BoardController;
import com.api.board.controller.BoardRepository;
import com.api.board.controller.BoardRepositoryImpl;
import com.api.board.controller.BoardService;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    public AppConfig() {
        controllerMap.put("/board/write", boardController);
        controllerMap.put("/board/read", boardController);
    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}

package com.common;

import com.api.board.controller.*;
import com.api.reply.controller.ReplyController;
import com.api.reply.controller.ReplyRepository;
import com.api.reply.controller.ReplyRepositoryImpl;
import com.api.reply.controller.ReplyService;
import com.api.user.controller.*;
import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final HikariDataSource ds = new HikariDataSource();

    private final BoardRepository boardRepository = new BoardCpRepositoryImpl(ds);
    private final BoardService boardService = new BoardService(boardRepository);
    private final BoardController boardController = new BoardController(boardService);

    private final UserRepository userRepository = new UserCpRepositoryImpl(ds);
    private final UserService userService = new UserService(userRepository);
    private final UserController userController = new UserController(userService);

    private final ReplyRepository replyRepository = new ReplyRepositoryImpl();
    private final ReplyService replyService = new ReplyService(replyRepository);
    private final ReplyController replyController = new ReplyController(replyService);

    public AppConfig() {
        ds.setJdbcUrl(System.getenv("DB_URL"));
        ds.setUsername(System.getenv("DB_USERNAME"));
        ds.setPassword(System.getenv("DB_PASSWORD"));

        controllerMap.put("/board/write", boardController);
        controllerMap.put("/board/read", boardController);
        controllerMap.put("/user/signup", userController);
        controllerMap.put("/user/login", userController);
        controllerMap.put("/reply", replyController);
    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}

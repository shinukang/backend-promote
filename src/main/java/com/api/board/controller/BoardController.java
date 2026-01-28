package com.api.board.controller;

import com.api.board.model.BoardDto;
import com.common.BaseResponse;
import com.common.Controller;
import com.utils.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardController implements Controller {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse response) {
        BoardDto.Request reqDto = JsonParser.from(req, BoardDto.Request.class);
        Object res;
        if (req.getServletPath().contains("write")) {
            res = boardService.write(reqDto);
        } else if (req.getServletPath().contains("read")) {
            res = boardService.read(reqDto);
        } else {
            res = null;
        }
        return res != null ? BaseResponse.success(res) : BaseResponse.failure(null);
    }
}

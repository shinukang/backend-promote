package com.api.board.controller;

import com.api.board.model.BoardDto;
import com.common.BaseResponse;
import com.utils.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/board/write", "/board/read"})
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("read")) {
            BoardDto.Request reqDto = JsonParser.from(req, BoardDto.Request.class);
            BoardService boardService = BoardService.getInstance();
            BoardDto.ReadResponse readRes = boardService.read(reqDto);
            BaseResponse res = BaseResponse.success(readRes);
            resp.getWriter().write(JsonParser.from(res));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("write")) {
            BoardDto.Request reqDto = JsonParser.from(req, BoardDto.Request.class);
            BoardService boardService = BoardService.getInstance();
            BoardDto.WriteResponse writeRes = boardService.write(reqDto);
            BaseResponse res = BaseResponse.success(writeRes);
            resp.getWriter().write(JsonParser.from(res));
        }
    }
}

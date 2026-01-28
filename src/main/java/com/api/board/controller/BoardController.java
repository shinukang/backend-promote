package com.api.board.controller;

import com.api.board.model.BoardDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.common.Config.*;

@WebServlet(urlPatterns = {"/board/write", "/board/read"})
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("read")) {
            BoardDto.Request reqDto = BoardDto.Request.create(req);
            BoardService boardService = BoardService.getInstance();
            BoardDto.ReadResponse readRes = boardService.read(reqDto);
            resp.getWriter().write(readRes.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("write")) {
            BoardDto.Request reqDto = BoardDto.Request.create(req);
            BoardService boardService = BoardService.getInstance();
            BoardDto.WriteResponse writeRes = boardService.write(reqDto);
            resp.getWriter().write(writeRes.toString());
        }
    }
}

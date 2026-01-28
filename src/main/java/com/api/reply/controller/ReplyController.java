package com.api.reply.controller;

import com.api.reply.model.ReplyDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static com.common.Config.*;

@WebServlet(urlPatterns = "/reply")
public class ReplyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReplyDto.Request reqDto = ReplyDto.Request.create(req);
        ReplyService replyService = new ReplyService();
        ReplyDto.Response resDto = replyService.write(reqDto);
        resp.getWriter().write(resDto.toString());
    }
}

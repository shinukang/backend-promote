package com.api.reply.controller;

import com.api.reply.model.ReplyDto;
import com.common.BaseResponse;
import com.utils.JsonParser;
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
        ReplyDto.Request reqDto = JsonParser.from(req, ReplyDto.Request.class);
        ReplyService replyService = ReplyService.getInstance();
        ReplyDto.Response resDto = replyService.write(reqDto);
        BaseResponse res = BaseResponse.success(resDto);
        resp.getWriter().write(JsonParser.from(res));
    }
}

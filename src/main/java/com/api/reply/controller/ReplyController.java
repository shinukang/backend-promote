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
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                final String sql = "INSERT INTO reply(contents, board_idx) VALUES (?, ?)";
                try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                    ReplyDto replyDto = ReplyDto.create(req);
                    pStatement.setString(1, replyDto.getContents());
                    pStatement.setString(2, replyDto.getBoard_idx());
                    pStatement.executeUpdate();
                    resp.getWriter().write("댓글 작성 완료");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

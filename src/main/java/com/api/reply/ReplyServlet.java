package com.api.reply;

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

@WebServlet(urlPatterns = {"/reply"})
public class ReplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                final String sql = "INSERT INTO reply(contents, board_idx) VALUES (?, ?)";
                try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                    pStatement.setString(1, req.getParameter("contents"));
                    pStatement.setString(2, req.getParameter("board_idx"));
                    pStatement.executeUpdate();
                    resp.getWriter().write("댓글 작성 완료");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

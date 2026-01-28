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
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    final String sql = "SELECT * FROM board LEFT JOIN reply ON reply.board_idx=board.idx WHERE board.idx=?";
                    try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                        BoardDto.Request reqDto = BoardDto.Request.create(req);
                        pStatement.setInt(1, Integer.parseInt(reqDto.getIdx()));
                        ResultSet rs = pStatement.executeQuery();
                        while (rs.next()) {
                            String title = rs.getString("title");
                            String contents = rs.getString("board.contents");
                            String replyContents = rs.getString("reply.contents");
                            resp.getWriter().write(title + " " + contents + " " + replyContents + "\n");
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("write")) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    final String sql = "INSERT INTO board(title, contents) VALUES (?, ?)";
                    try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                        BoardDto.Request reqDto = BoardDto.Request.create(req);
                        pStatement.setString(1, reqDto.getTitle());
                        pStatement.setString(2, reqDto.getContents());
                        pStatement.executeUpdate();
                        resp.getWriter().write("게시글 작성 완료");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

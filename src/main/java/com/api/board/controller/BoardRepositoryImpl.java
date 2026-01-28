package com.api.board.controller;

import com.api.board.model.BoardDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.common.Config.*;

public class BoardRepositoryImpl implements BoardRepository {
    @Override
    public BoardDto.ReadResponse read(BoardDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                final String sql = "SELECT * FROM board LEFT JOIN reply ON reply.board_idx=board.idx WHERE board.idx=?";
                try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                    pStatement.setInt(1, Integer.parseInt(reqDto.getIdx()));
                    ResultSet rs = pStatement.executeQuery();
                    String title = "";
                    String contents = "";
                    List<String> replies = new ArrayList<>();
                    while (rs.next()) {
                        title = rs.getString("title");
                        contents = rs.getString("board.contents");
                        replies.add(rs.getString("reply.contents"));
                    }
                    return BoardDto.ReadResponse.create(title, contents, replies);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BoardDto.WriteResponse write(BoardDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                final String sql = "INSERT INTO board(title, contents) VALUES (?, ?)";
                try (PreparedStatement pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pStatement.setString(1, reqDto.getTitle());
                    pStatement.setString(2, reqDto.getContents());
                    pStatement.executeUpdate();
                    ResultSet rs = pStatement.getGeneratedKeys();
                    return BoardDto.WriteResponse.create(rs.next() ? rs.getString("insert_id") : "-1");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

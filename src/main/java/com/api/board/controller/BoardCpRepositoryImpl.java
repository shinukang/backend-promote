package com.api.board.controller;

import com.api.board.model.BoardDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardCpRepositoryImpl implements BoardRepository {
    private final DataSource ds;

    public BoardCpRepositoryImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public BoardDto.WriteResponse write(BoardDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = ds.getConnection()) {
                PreparedStatement pStmt = conn.prepareStatement("INSERT INTO board(title, contents) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                pStmt.setString(1, reqDto.getTitle());
                pStmt.setString(2, reqDto.getContents());
                pStmt.executeUpdate();
                ResultSet rs = pStmt.getGeneratedKeys();
                return BoardDto.WriteResponse.create(rs.next() ? rs.getString("insert_id") : "-1");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BoardDto.ReadResponse read(BoardDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = ds.getConnection()) {
                PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM board LEFT JOIN reply ON reply.board_idx=board.idx WHERE board.idx=?");
                pStmt.setInt(1, Integer.parseInt(reqDto.getIdx()));
                ResultSet rs = pStmt.executeQuery();
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
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

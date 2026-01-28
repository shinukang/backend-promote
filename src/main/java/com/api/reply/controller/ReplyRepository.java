package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

import java.sql.*;

import static com.common.Config.*;

public class ReplyRepository {
    public ReplyDto.Response write(ReplyDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                final String sql = "INSERT INTO reply(contents, board_idx) VALUES (?, ?)";
                PreparedStatement pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pStatement.setString(1, reqDto.getContents());
                pStatement.setString(2, reqDto.getBoard_idx());
                pStatement.executeUpdate();
                ResultSet rs = pStatement.getGeneratedKeys();
                return ReplyDto.Response.create(rs.next() ? rs.getString("insert_id") : "-1");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

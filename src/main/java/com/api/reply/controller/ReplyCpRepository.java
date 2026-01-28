package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

import javax.sql.DataSource;
import java.sql.*;

public class ReplyCpRepository implements ReplyRepository{
    private final DataSource ds;

    public ReplyCpRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public ReplyDto.Response write(ReplyDto.Request reqDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = ds.getConnection()) {
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

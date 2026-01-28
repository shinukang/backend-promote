package com.api.reply.model;

import jakarta.servlet.http.HttpServletRequest;

public class ReplyDto {
    private String contents;
    private String board_idx;

    private ReplyDto(String contents, String board_idx) {
        this.contents = contents;
        this.board_idx = board_idx;
    }

    public String getContents() {
        return contents;
    }

    public String getBoard_idx() {
        return board_idx;
    }

    public static ReplyDto create(HttpServletRequest req) {
        String contents = req.getParameter("contents");
        String idx = req.getParameter("board_idx");
        return new ReplyDto(contents, idx);
    }
}

package com.api.reply.model;

import jakarta.servlet.http.HttpServletRequest;

public class ReplyDto {

    public static class Request {
        private String contents;
        private String board_idx;

        private Request(String contents, String board_idx) {
            this.contents = contents;
            this.board_idx = board_idx;
        }

        public String getContents() {
            return contents;
        }

        public String getBoard_idx() {
            return board_idx;
        }

        @Override
        public String toString() {
            return "ReplyDto{" +
                    "contents='" + contents + '\'' +
                    ", board_idx='" + board_idx + '\'' +
                    '}';
        }

        public static Request create(HttpServletRequest req) {
            String contents = req.getParameter("contents");
            String idx = req.getParameter("board_idx");
            return new Request(contents, idx);
        }
    }

    public static class Response {
        private String idx;

        private Response(String idx) {
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "idx='" + idx + '\'' +
                    '}';
        }

        public static Response create(String idx) {
            return new Response(idx);
        }
    }
}

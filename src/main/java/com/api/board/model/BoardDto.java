package com.api.board.model;

import jakarta.servlet.http.HttpServletRequest;

public class BoardDto {

    public static class Request {
        private String idx;
        private String title;
        private String contents;

        private Request(String idx, String title, String contents) {
            this.idx = idx;
            this.title = title;
            this.contents = contents;
        }

        public String getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public static Request create(HttpServletRequest req) {
            String idx = req.getParameter("idx");
            String title = req.getParameter("title");
            String contents = req.getParameter("contents");
            return new Request(idx, title, contents);
        }
    }

    public static class WriteResponse {
        private String idx;

        private WriteResponse(String idx) {
            this.idx = idx;
        }

        public static WriteResponse create(String idx) {
            return new WriteResponse(idx);
        }
    }

    public static class ReadResponse {
        private String title;
        private String contents;

        private ReadResponse(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }

        public static ReadResponse create(String title, String contents) {
            return new ReadResponse(title, contents);
        }
    }
}

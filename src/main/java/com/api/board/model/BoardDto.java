package com.api.board.model;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

        @Override
        public String toString() {
            return "WriteResponse{" +
                    "idx='" + idx + '\'' +
                    '}';
        }

        public static WriteResponse create(String idx) {
            return new WriteResponse(idx);
        }
    }

    public static class ReadResponse {
        private String title;
        private String contents;
        private List<String> replies;

        private ReadResponse(String title, String contents, List<String> replies) {
            this.title = title;
            this.contents = contents;
            this.replies = replies;
        }

        @Override
        public String toString() {
            return "ReadResponse{" +
                    "title='" + title + '\'' +
                    ", contents='" + contents + '\'' +
                    ", replies=" + replies +
                    '}';
        }

        public static ReadResponse create(String title, String contents, List<String> replies) {
            return new ReadResponse(title, contents, replies);
        }
    }
}

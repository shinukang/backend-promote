package com.api.board.model;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BoardDto {

    public static class Request {
        private String idx;
        private String title;
        private String contents;

        public Request() {

        }

        private Request(String idx, String title, String contents) {
            this.idx = idx;
            this.title = title;
            this.contents = contents;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
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

        public WriteResponse() {

        }

        private WriteResponse(String idx) {
            this.idx = idx;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public static WriteResponse create(String idx) {
            return new WriteResponse(idx);
        }
    }

    public static class ReadResponse {
        private String title;
        private String contents;
        private List<String> replies;

        public ReadResponse() {

        }

        private ReadResponse(String title, String contents, List<String> replies) {
            this.title = title;
            this.contents = contents;
            this.replies = replies;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public List<String> getReplies() {
            return replies;
        }

        public void setReplies(List<String> replies) {
            this.replies = replies;
        }

        public static ReadResponse create(String title, String contents, List<String> replies) {
            return new ReadResponse(title, contents, replies);
        }
    }
}

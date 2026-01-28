package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

public class ReplyService {
    private ReplyService() {

    }

    private static class SingletonHolder {
        private static final ReplyService instance = new ReplyService();
    }

    public static ReplyService getInstance() {
        return SingletonHolder.instance;
    }

    public ReplyDto.Response write(ReplyDto.Request reqDto) {
        ReplyRepository replyRepository = ReplyRepository.getInstance();
        return replyRepository.write(reqDto);
    }
}

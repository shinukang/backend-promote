package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public ReplyDto.Response write(ReplyDto.Request reqDto) {
        return replyRepository.write(reqDto);
    }
}

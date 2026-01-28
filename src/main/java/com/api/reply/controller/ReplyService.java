package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

public class ReplyService {
    public ReplyDto.Response write(ReplyDto.Request reqDto) {
        ReplyRepository replyRepository = new ReplyRepository();
        return replyRepository.write(reqDto);
    }
}

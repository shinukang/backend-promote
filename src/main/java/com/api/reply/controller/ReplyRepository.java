package com.api.reply.controller;

import com.api.reply.model.ReplyDto;

public interface ReplyRepository {
    ReplyDto.Response write(ReplyDto.Request reqDto);
}
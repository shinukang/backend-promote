package com.api.reply.controller;

import com.api.reply.model.ReplyDto;
import com.common.BaseResponse;
import com.common.Controller;
import com.utils.JsonParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ReplyController implements Controller {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse response) {
        ReplyDto.Request reqDto = JsonParser.from(req, ReplyDto.Request.class);
        ReplyDto.Response resDto = replyService.write(reqDto);
        return BaseResponse.success(resDto);
    }
}

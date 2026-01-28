package com.api.board.controller;

import com.api.board.model.BoardDto;

public interface BoardRepository {
    BoardDto.WriteResponse write(BoardDto.Request reqDto);
    BoardDto.ReadResponse read(BoardDto.Request reqDto);
}

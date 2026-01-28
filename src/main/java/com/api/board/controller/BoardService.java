package com.api.board.controller;

import com.api.board.model.BoardDto;

public class BoardService {

    public BoardDto.WriteResponse write(BoardDto.Request reqDto) {
        BoardRepository boardRepository = new BoardRepository();
        return boardRepository.write(reqDto);
    }

    public BoardDto.ReadResponse read(BoardDto.Request reqDto) {
        BoardRepository boardRepository = new BoardRepository();
        return boardRepository.read(reqDto);
    }
}

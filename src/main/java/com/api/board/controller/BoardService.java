package com.api.board.controller;

import com.api.board.model.BoardDto;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardDto.WriteResponse write(BoardDto.Request reqDto) {
        return boardRepository.write(reqDto);
    }

    public BoardDto.ReadResponse read(BoardDto.Request reqDto) {
        return boardRepository.read(reqDto);
    }
}

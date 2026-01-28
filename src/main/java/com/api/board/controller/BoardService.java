package com.api.board.controller;

import com.api.board.model.BoardDto;

public class BoardService {

    private BoardService() {

    }

    private static class SingletonHolder {
        private static final BoardService instance = new BoardService();
    }

    public static BoardService getInstance() {
        return SingletonHolder.instance;
    }

    public BoardDto.WriteResponse write(BoardDto.Request reqDto) {
        BoardRepository boardRepository = BoardRepository.getInstance();
        return boardRepository.write(reqDto);
    }

    public BoardDto.ReadResponse read(BoardDto.Request reqDto) {
        BoardRepository boardRepository = BoardRepository.getInstance();
        return boardRepository.read(reqDto);
    }
}

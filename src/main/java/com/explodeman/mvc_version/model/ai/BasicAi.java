package com.explodeman.mvc_version.model.ai;

import com.explodeman.mvc_version.model.board.Board;
import com.explodeman.mvc_version.model.board.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class BasicAi  implements Ai{
    private final Board board;

    public BasicAi(Board board) {
        this.board = board;
    }


    @Override
    public Position input() {
        List<Position> moves = new ArrayList<>();
        for (int row = 0; row < board.side(); row++) {
            for (int column = 0; column < board.side(); column++) {
                if(board.isFree(row, column)) {
                    moves.add(new Position(row, column));
                }
            }
        }
        Collections.shuffle(moves);
        return moves.get(0);
    }


}

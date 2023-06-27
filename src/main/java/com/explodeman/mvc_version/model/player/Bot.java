package com.explodeman.mvc_version.model.player;

import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.ai.Ai;
import com.explodeman.mvc_version.model.board.Position;

public class Bot extends Player{
    private final Ai ai;

    public Bot(Figure figure, Ai ai) {
        super(figure);
        this.ai = ai;
    }

    public Position input() {
        return ai.input();
    }
}

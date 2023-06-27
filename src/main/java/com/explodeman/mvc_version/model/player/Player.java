package com.explodeman.mvc_version.model.player;

import com.explodeman.mvc_version.model.Figure;

public class Player {
    final Figure figure;

    public Player(Figure figure) {
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }
}

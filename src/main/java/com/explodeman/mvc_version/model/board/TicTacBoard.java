package com.explodeman.mvc_version.model.board;

import com.explodeman.mvc_version.model.Figure;

public class TicTacBoard extends BoardImp<Figure>{
    public TicTacBoard(Figure emptyValue) {
        super(3, emptyValue);
    }

}

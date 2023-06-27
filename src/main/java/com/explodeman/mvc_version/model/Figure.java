package com.explodeman.mvc_version.model;

public enum Figure {
    NULL(' '),
    X('X'),
    ZERO('0');

    private final char symbol;

    Figure(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

/*
    private int binaryMoves(Predicate<Figure> predicate) {
        int moves = 0;
        int mask = 1;
        for (int i = 0; i < side; i++) {
            for (int n = 0; n < side; n++) {
                if (predicate.test(array[i][n])) {
                    moves = moves | mask;
                }
                mask = mask << 1;
            }
        }
        return moves;
    }
 */

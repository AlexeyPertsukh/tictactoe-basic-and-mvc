package com.explodeman.mvc_version.model.board;

import java.util.Arrays;

public class BoardImp<T> implements Board<T> {
    private final int side;
    private final Object[][] values;
    private final T emptyValue;

    public BoardImp(int side, T emptyValue) {
        this.side = side;
        this.emptyValue = emptyValue;

        this.values = new Object[side][side];
        for (Object[] value : values) {
            Arrays.fill(value, emptyValue);
        }
    }

    private BoardImp(Board<T> board) {
        this.side = board.side();
        this.emptyValue = board.emptyValue();
        this.values = new Object[side][side];

        for (int row = 0; row < values.length; row++) {
            for (int column = 0; column < values.length; column++) {
                this.values[row][column] = board.get(row, column);
            }
        }
    }

    @Override
    public int side() {
        return side;
    }

    @Override
    public T emptyValue() {
        return emptyValue;
    }

    @Override
    public void put(int row, int column, T value) {
        if (!isFree(row, column)) {
            String message = String.format("cell is occupied: row=%d, column=%d", row, column);
            throw new IllegalArgumentException(message);
        }
        values[row][column] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int row, int column) {
        return (T) values[row][column];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int row, int column) {
        T out = (T) values[row][column];
        values[row][column] = emptyValue;
        return out;
    }

    @Override
    public boolean isFree(int row, int column) {
        return values[row][column] == emptyValue;
    }

    @Override
    public Board<T> clone() {
        return new BoardImp<>(this);
    }
    @Override
    public void put(Position position, T value) {
        put(position.row, position.column, value);
    }
    @Override
    public T get(Position position) {
        return get(position.row, position.column);
    }
    @Override
    public T remove(Position position) {
        return remove(position.row, position.column);
    }
    @Override
    public boolean isFree(Position position) {
        return isFree(position.row, position.column);
    }

}

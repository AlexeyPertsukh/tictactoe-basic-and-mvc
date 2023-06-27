package com.explodeman.mvc_version.model.board;

public interface Board<T> {
    int side();

    T emptyValue();

    void put(int row, int column, T value);

    T get(int row, int column);

    T remove(int row, int column);

    boolean isFree(int row, int column);

    Board<T> clone();

    void put(Position position, T value);

    T get(Position position);

    T remove(Position position);

    boolean isFree(Position position);
}

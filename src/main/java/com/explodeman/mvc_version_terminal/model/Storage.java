package com.explodeman.mvc_version_terminal.model;

public interface Storage<T> {
    void set(T value);
    T get();

    void and(T value);

    void or(T value);
}

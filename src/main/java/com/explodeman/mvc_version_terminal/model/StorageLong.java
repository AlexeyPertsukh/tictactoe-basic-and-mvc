package com.explodeman.mvc_version_terminal.model;

public class StorageLong implements Storage<Long> {
    private long value;

    @Override
    public void set(Long value) {
        this.value = value;
    }

    @Override
    public Long get() {
        return value;
    }

    @Override
    public void and(Long value) {
        this.value &= value;
    }

    @Override
    public void or(Long value) {
        this.value |= value;
    }
}

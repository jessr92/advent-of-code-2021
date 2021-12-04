package com.gordonreid.adventofcode2021.december04;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cell {
    private final int number;
    private boolean marked = false;

    public void mark() {
        marked = true;
    }

    public boolean marked() {
        return marked;
    }

    public boolean notMarked() {
        return !marked();
    }

    public int getNumber() {
        return number;
    }
}

package com.gordonreid.adventofcode2021.december04;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cell {
    private final int number;
    private boolean picked = false;

    public void pick() {
        picked = true;
    }

    public boolean picked() {
        return picked;
    }

    public boolean notPicked() {
        return !picked();
    }

    public int getNumber() {
        return number;
    }
}

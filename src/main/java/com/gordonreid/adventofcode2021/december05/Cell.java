package com.gordonreid.adventofcode2021.december05;

import lombok.Getter;

@Getter
public class Cell {
    private int count = 0;

    public void addVent() {
        count++;
    }
}

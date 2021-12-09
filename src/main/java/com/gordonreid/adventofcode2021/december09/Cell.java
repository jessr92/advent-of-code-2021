package com.gordonreid.adventofcode2021.december09;

import lombok.Getter;

@Getter
public class Cell {
    private final boolean partOfBasin;
    private boolean visited;

    public Cell(boolean partOfBasin) {
        this.partOfBasin = partOfBasin;
        this.visited = false;
    }

    public void visit() {
        visited = true;
    }
}

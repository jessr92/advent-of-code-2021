package com.gordonreid.adventofcode2021.helpers;

public record Coordinates(int x, int y) {

    public Coordinates moveForward(int forward) {
        return new Coordinates(x + forward, y);
    }

    public Coordinates moveUp(int up) {
        return new Coordinates(x, y + up);
    }
}

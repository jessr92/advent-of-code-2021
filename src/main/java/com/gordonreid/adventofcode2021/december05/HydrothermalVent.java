package com.gordonreid.adventofcode2021.december05;

@SuppressWarnings("unused") // IntelliJ marks this as unused sometimes then realises it is used. Suppressing.
public record HydrothermalVent(int startX, int startY, int endX, int endY) {

    public static HydrothermalVent create(String input) {
        String start = input.split(" -> ")[0];
        String end = input.split(" -> ")[1];
        int startX = Integer.parseInt(start.split(",")[0]);
        int startY = Integer.parseInt(start.split(",")[1]);
        int endX = Integer.parseInt(end.split(",")[0]);
        int endY = Integer.parseInt(end.split(",")[1]);
        return new HydrothermalVent(startX, startY, endX, endY);
    }

    public boolean isHorizontal() {
        return startY == endY;
    }

    public boolean isVertical() {
        return startX == endX;
    }

    public int highestXValue() {
        return Math.max(startX, endX);
    }

    public int highestYValue() {
        return Math.max(startY, endY);
    }
}

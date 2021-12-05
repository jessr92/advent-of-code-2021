package com.gordonreid.adventofcode2021.december05;

import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("unused") // IntelliJ marks this as unused sometimes then realises it is used. Suppressing.
public record Grid(Cell[][] cells) {

    private record Pair(int x, int y) {
    }

    public static Grid create(int xCount, int yCount) {
        Cell[][] cells = new Cell[yCount][xCount];
        for (int x = 0; x < xCount; x++) {
            for (int y = 0; y < yCount; y++) {
                cells[y][x] = new Cell();
            }
        }
        return new Grid(cells);
    }

    public void mark(HydrothermalVent vent) {
        int startX = vent.startX();
        int endX = vent.endX();
        int startY = vent.startY();
        int endY = vent.endY();
        Stream<Integer> xStream = stream(startX, endX);
        Stream<Integer> yStream = stream(startY, endY);
        Stream<Pair> pairStream = Streams.zip(xStream, yStream, Pair::new);
        // Handle case where vent is one cell in length. The pair stream will be infinite in length because both
        // xStream and yStream will be infinite in size so the zip of them will be infinite in size.
        if (startX == endX && startY == endY) {
            pairStream = pairStream.limit(1);
        }
        pairStream.forEach(pair -> cells[pair.y][pair.x].addVent());
    }

    private static Stream<Integer> stream(int from, int to) {
        if (from < to) {
            return IntStream.rangeClosed(from, to).boxed();
        } else if (from > to) {
            return IntStream.rangeClosed(to, from)
                    .boxed()
                    .sorted(Collections.reverseOrder());
        } else {
            return IntStream.generate(() -> from).boxed();
        }
    }


    public List<Cell> cellsWithMultipleVents() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getCount() > 1)
                .toList();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int count = cell.getCount();
                if (count == 0) {
                    builder.append(".");
                } else {
                    builder.append(count);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}

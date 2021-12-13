package com.gordonreid.adventofcode2021.december13;

import com.google.common.primitives.Booleans;
import com.gordonreid.adventofcode2021.helpers.GridHelpers.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DottedPaper {

    private boolean[][] dottedPaper;
    private final List<String> instructions;

    public DottedPaper(List<String> input) {
        this.instructions = new ArrayList<>();
        List<Coordinates> dots = new ArrayList<>();
        boolean blankLineDetected = false;
        for (String line : input) {
            if (line.isBlank()) {
                blankLineDetected = true;
            } else if (!blankLineDetected) {
                // The lines before the blank line indicate dots on the paper
                String[] coordinateString = line.split(",");
                int x = Integer.parseInt(coordinateString[0]);
                int y = Integer.parseInt(coordinateString[1]);
                dots.add(new Coordinates(x, y));
            } else {
                // The lines after the blank line are fold instructions. We only need to know the axis the fold is
                // along because all folds are folds in half.
                instructions.add(line.split(" ")[2].split("=")[0]);
            }
        }
        int xSize = 1 + dots.stream().map(Coordinates::x).max(Integer::compare).orElse(0);
        int ySize = 1 + dots.stream().map(Coordinates::y).max(Integer::compare).orElse(0);
        this.dottedPaper = new boolean[ySize][xSize];
        dots.forEach(dot -> this.dottedPaper[dot.y()][dot.x()] = true);
    }

    public long dotCount() {
        return Arrays.stream(dottedPaper).map(Booleans::asList).flatMap(Collection::stream).filter(a -> a).count();
    }

    void allFolds() {
        fold(instructions.size());
    }

    void fold(int folds) {
        for (int i = 0; i < folds; i++) {
            int originalXSize = dottedPaper[0].length;
            int originalYSize = dottedPaper.length;
            boolean[][] foldedDottedPaper;
            String axis = instructions.get(i);
            if (axis.equals("y")) {
                // horizontal fold. x size stays the same. y size halves.
                int newYSize = originalYSize / 2;
                foldedDottedPaper = new boolean[newYSize][originalXSize];
                for (int x = 0; x < originalXSize; x++) {
                    for (int y = 0; y < newYSize; y++) {
                        foldedDottedPaper[y][x] = dottedPaper[y][x] || dottedPaper[originalYSize - y - 1][x];
                    }
                }
            } else if (axis.equals("x")) {
                // vertical fold. x size halves. y size stays the same
                int newXSize = originalXSize / 2;
                foldedDottedPaper = new boolean[originalYSize][newXSize];
                for (int x = 0; x < newXSize; x++) {
                    for (int y = 0; y < originalYSize; y++) {
                        foldedDottedPaper[y][x] = dottedPaper[y][x] || dottedPaper[y][originalXSize - x - 1];
                    }
                }
            } else {
                throw new IllegalStateException("Axis can only be x or y but was " + axis);
            }
            this.dottedPaper = foldedDottedPaper;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (boolean[] dotRow : dottedPaper) {
            for (boolean dot : dotRow) {
                builder.append(dot ? "#" : " ");
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}

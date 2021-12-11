package com.gordonreid.adventofcode2021.december11;

import com.google.common.primitives.Booleans;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Grid {

    private static final int GRID_X_SIZE = 10;
    private static final int GRID_Y_SIZE = 10;

    private final int[][] energyLevels;

    public record Result(long flashes, boolean allFlashed) {
    }

    public Grid(List<String> input) {
        this.energyLevels = new int[GRID_Y_SIZE][GRID_X_SIZE];
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                char character = input.get(y).charAt(x);
                int initialEnergyLevel = Integer.parseInt(String.valueOf(character));
                this.energyLevels[y][x] = initialEnergyLevel;
            }
        }
    }

    private boolean inBounds(int x, int y) {
        boolean outOfBounds = y < 0 || y >= energyLevels.length || x < 0 || x >= energyLevels[y].length;
        return !outOfBounds;
    }

    public Result simulateStep() {
        boolean[][] toFlash = new boolean[GRID_Y_SIZE][GRID_X_SIZE];
        boolean[][] flashed = new boolean[GRID_Y_SIZE][GRID_X_SIZE];
        // Increment energy levels of every octopus
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                energyLevels[y][x]++;
                toFlash[y][x] = energyLevels[y][x] > 9;
            }
        }
        // While there has been a flash propagating through the grid, check there aren't any more flashes to propagate.
        boolean changed;
        do {
            changed = false;
            for (int y = 0; y < GRID_Y_SIZE; y++) {
                for (int x = 0; x < GRID_X_SIZE; x++) {
                    if (toFlash[y][x] && !flashed[y][x]) {
                        flashed[y][x] = true;
                        changed |= isChanged(toFlash, x - 1, y - 1);
                        changed |= isChanged(toFlash, x, y - 1);
                        changed |= isChanged(toFlash, x + 1, y - 1);
                        changed |= isChanged(toFlash, x - 1, y);
                        changed |= isChanged(toFlash, x + 1, y);
                        changed |= isChanged(toFlash, x - 1, y + 1);
                        changed |= isChanged(toFlash, x, y + 1);
                        changed |= isChanged(toFlash, x + 1, y + 1);
                    }
                }
            }
        } while (changed);

        // All flashed octopuses have their energy levels reset to 0
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                if (flashed[y][x]) {
                    energyLevels[y][x] = 0;
                }
            }
        }

        long flashCount = Arrays.stream(flashed).map(Booleans::asList).flatMap(Collection::stream).filter(a -> a).count();
        return new Result(flashCount, flashCount == GRID_X_SIZE * GRID_Y_SIZE);
    }

    private boolean isChanged(boolean[][] toFlash, int x, int y) {
        if (inBounds(x, y) && !toFlash[y][x]) {
            energyLevels[y][x]++;
            if (energyLevels[y][x] > 9) {
                toFlash[y][x] = true;
                return true;
            }
        }
        return false;
    }
}

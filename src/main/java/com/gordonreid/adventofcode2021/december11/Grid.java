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

    public Result simulateStep() {
        incrementEnergyLevels();
        boolean[][] flashed = new boolean[GRID_Y_SIZE][GRID_X_SIZE];
        // While there has been a flash propagating through the grid, check there aren't any more flashes to propagate.
        boolean changed;
        do {
            changed = false;
            for (int y = 0; y < GRID_Y_SIZE; y++) {
                for (int x = 0; x < GRID_X_SIZE; x++) {
                    if (energyLevels[y][x] > 9 && !flashed[y][x]) {
                        flashed[y][x] = true;
                        changed = true;
                        propagateFlash(y, x);
                    }
                }
            }
        } while (changed);
        resetEnergyLevelsForFlashedOctopi(flashed);
        long flashCount = Arrays.stream(flashed).map(Booleans::asList).flatMap(Collection::stream).filter(a -> a).count();
        return new Result(flashCount, flashCount == GRID_X_SIZE * GRID_Y_SIZE);
    }

    private void incrementEnergyLevels() {
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                incrementEnergy(x, y);
            }
        }
    }

    private void resetEnergyLevelsForFlashedOctopi(boolean[][] flashed) {
        // All flashed octopuses have their energy levels reset to 0
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                if (flashed[y][x]) {
                    energyLevels[y][x] = 0;
                }
            }
        }
    }

    private boolean inBounds(int x, int y) {
        boolean outOfBounds = y < 0 || y >= energyLevels.length || x < 0 || x >= energyLevels[y].length;
        return !outOfBounds;
    }

    private void incrementEnergy(int x, int y) {
        if (inBounds(x, y)) {
            energyLevels[y][x]++;
        }
    }

    private void propagateFlash(int y, int x) {
        incrementEnergy(x - 1, y - 1);
        incrementEnergy(x, y - 1);
        incrementEnergy(x + 1, y - 1);
        incrementEnergy(x - 1, y);
        incrementEnergy(x + 1, y);
        incrementEnergy(x - 1, y + 1);
        incrementEnergy(x, y + 1);
        incrementEnergy(x + 1, y + 1);
    }
}

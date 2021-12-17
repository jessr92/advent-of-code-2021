package com.gordonreid.adventofcode2021.december11;

import com.google.common.primitives.Booleans;
import com.gordonreid.adventofcode2021.helpers.Coordinates;

import java.util.*;

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
                this.energyLevels[y][x] = input.get(y).charAt(x) - '0';
            }
        }
    }

    public Result simulateStep() {
        Deque<Coordinates> octopiToVisit = new ArrayDeque<>();
        incrementEnergyLevels(octopiToVisit);
        boolean[][] flashed = new boolean[GRID_Y_SIZE][GRID_X_SIZE];
        // Octopi to visit are ready to flash. This may result in more octopi being ready to flash so we keep going
        // until all Octopi have flashed or don't have enough energy to flash yet.
        while (!octopiToVisit.isEmpty()) {
            Coordinates coordinates = octopiToVisit.pop();
            int x = coordinates.x();
            int y = coordinates.y();
            if (energyLevels[y][x] > 9 && !flashed[y][x]) {
                flashed[y][x] = true;
                propagateFlash(octopiToVisit, y, x);
            }
        }
        resetEnergyLevelsForFlashedOctopi(flashed);
        long flashCount = Arrays.stream(flashed).map(Booleans::asList).flatMap(Collection::stream).filter(a -> a).count();
        return new Result(flashCount, flashCount == GRID_X_SIZE * GRID_Y_SIZE);
    }

    private void incrementEnergyLevels(Deque<Coordinates> octopiToVisit) {
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                incrementEnergy(octopiToVisit, x, y);
            }
        }
    }

    private void resetEnergyLevelsForFlashedOctopi(boolean[][] flashed) {
        // All octopi that have flashed have their energy levels reset to 0
        for (int y = 0; y < GRID_Y_SIZE; y++) {
            for (int x = 0; x < GRID_X_SIZE; x++) {
                energyLevels[y][x] = flashed[y][x] ? 0 : energyLevels[y][x];
            }
        }
    }

    private boolean inBounds(int x, int y) {
        return y >= 0 && y < GRID_Y_SIZE && x >= 0 && x < GRID_X_SIZE;
    }

    private void incrementEnergy(Deque<Coordinates> octopiToVisit, int x, int y) {
        if (inBounds(x, y)) {
            energyLevels[y][x]++;
            if (energyLevels[y][x] > 9) {
                octopiToVisit.add(new Coordinates(x, y));
            }
        }
    }

    private void propagateFlash(Deque<Coordinates> octopiToVisit, int y, int x) {
        incrementEnergy(octopiToVisit, x - 1, y - 1);
        incrementEnergy(octopiToVisit, x, y - 1);
        incrementEnergy(octopiToVisit, x + 1, y - 1);
        incrementEnergy(octopiToVisit, x - 1, y);
        incrementEnergy(octopiToVisit, x + 1, y);
        incrementEnergy(octopiToVisit, x - 1, y + 1);
        incrementEnergy(octopiToVisit, x, y + 1);
        incrementEnergy(octopiToVisit, x + 1, y + 1);
    }
}

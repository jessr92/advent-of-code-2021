package com.gordonreid.adventofcode2021.december09;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class HeightMap {
    int[][] heightMap;
    boolean[][] visited;
    int xAxisSize;
    int yAxisSize;

    public HeightMap(List<String> input) {
        this.yAxisSize = input.size();
        this.xAxisSize = input.get(0).length();
        this.heightMap = new int[yAxisSize][xAxisSize];
        this.visited = new boolean[yAxisSize][xAxisSize];

        for (int y = 0; y < yAxisSize; y++) {
            char[] line = input.get(y).toCharArray();
            for (int x = 0; x < xAxisSize; x++) {
                heightMap[y][x] = Integer.parseInt(String.valueOf(line[x]));
            }
        }
    }

    public List<Integer> getLowestPoints() {
        List<Integer> lowestPoints = new ArrayList<>();
        for (int x = 0; x < xAxisSize; x++) {
            for (int y = 0; y < yAxisSize; y++) {
                if (isLowest(x, y)) {
                    lowestPoints.add(valueAt(x, y));
                }
            }
        }
        return lowestPoints;
    }

    public List<Integer> getBasinSizes() {
        List<Integer> basinSizes = new ArrayList<>();
        for (int x = 0; x < xAxisSize; x++) {
            for (int y = 0; y < yAxisSize; y++) {
                basinSizes.add(basinSize(x, y));
            }
        }
        return basinSizes;
    }

    private int valueAt(int x, int y) {
        boolean outOfBounds = y < 0 || y >= heightMap.length || x < 0 || x >= heightMap[y].length;
        return outOfBounds ? 9 : heightMap[y][x];
    }

    private int basinSize(int x, int y) {
        // Points with height 9 are not counted as being part of the basin. We also ensure we don't double count points
        if (valueAt(x, y) == 9 || visited[y][x]) {
            return 0;
        }
        visited[y][x] = true;
        return 1 + basinSize(x, y - 1) + basinSize(x, y + 1) + basinSize(x - 1, y) + basinSize(x + 1, y);
    }

    private boolean isLowest(int x, int y) {
        int currentPoint = valueAt(x, y);
        boolean lowerThanNorthernPoint = currentPoint < valueAt(x, y - 1);
        boolean lowerThanSouthernPoint = currentPoint < valueAt(x, y + 1);
        boolean lowerThanWesterlyPoint = currentPoint < valueAt(x - 1, y);
        boolean lowerThanEasterlyPoint = currentPoint < valueAt(x + 1, y);

        return lowerThanNorthernPoint && lowerThanSouthernPoint && lowerThanWesterlyPoint && lowerThanEasterlyPoint;
    }
}

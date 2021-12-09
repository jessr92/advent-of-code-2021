package com.gordonreid.adventofcode2021.december09;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class HeightMapHelpers {

    static boolean isLowest(int x, int y, int[][] heightMap) {
        boolean noNorthernPoint = y < 1;
        boolean noSouthernPoint = y >= heightMap.length - 1;
        boolean noWesterlyPoint = x < 1;
        boolean noEasterlyPoint = x >= heightMap[y].length - 1;

        boolean lowerThanNorthernPoint = noNorthernPoint || heightMap[y][x] < heightMap[y - 1][x];
        boolean lowerThanSouthernPoint = noSouthernPoint || heightMap[y][x] < heightMap[y + 1][x];
        boolean lowerThanWesterlyPoint = noWesterlyPoint || heightMap[y][x] < heightMap[y][x - 1];
        boolean lowerThanEasterlyPoint = noEasterlyPoint || heightMap[y][x] < heightMap[y][x + 1];

        return lowerThanNorthernPoint && lowerThanSouthernPoint && lowerThanWesterlyPoint && lowerThanEasterlyPoint;
    }

    static boolean isPartOfBasin(int x, int y, int[][] heightMap) {
        if (heightMap[y][x] == 9) {
            return false; // Points with height 9 are not counted as being part of the basin
        }

        boolean hasNorthernPoint = y > 0;
        boolean hasSouthernPoint = y < heightMap.length - 1;
        boolean hasWesterlyPoint = x > 0;
        boolean hasEasterlyPoint = x < heightMap[y].length - 1;

        boolean canFlowToNorthernPoint = hasNorthernPoint && heightMap[y][x] > heightMap[y - 1][x];
        boolean canFlowToSouthernPoint = hasSouthernPoint && heightMap[y][x] > heightMap[y + 1][x];
        boolean canFlowToWesterlyPoint = hasWesterlyPoint && heightMap[y][x] > heightMap[y][x - 1];
        boolean canFlowToEasterlyPoint = hasEasterlyPoint && heightMap[y][x] > heightMap[y][x + 1];

        return isLowest(x, y, heightMap) || canFlowToNorthernPoint || canFlowToSouthernPoint || canFlowToWesterlyPoint || canFlowToEasterlyPoint;
    }

    static int[][] getHeightMap(List<String> input) {
        int yAxisSize = input.size();
        int xAxisSize = input.get(0).length();
        int[][] heightMap = new int[yAxisSize][xAxisSize];

        for (int y = 0; y < yAxisSize; y++) {
            char[] line = input.get(y).toCharArray();
            for (int x = 0; x < xAxisSize; x++) {
                heightMap[y][x] = Integer.parseInt(String.valueOf(line[x]));
            }
        }
        return heightMap;
    }

    static Cell[][] getDownwardFlowMap(List<String> input) {
        int[][] heightMap = getHeightMap(input);
        Cell[][] downwardFlowMap = new Cell[heightMap.length][heightMap[0].length];

        for (int y = 0; y < heightMap.length; y++) {
            for (int x = 0; x < heightMap[y].length; x++) {
                downwardFlowMap[y][x] = new Cell(isPartOfBasin(x, y, heightMap));
            }
        }
        return downwardFlowMap;
    }

}

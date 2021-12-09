package com.gordonreid.adventofcode2021.december09;

import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        int[][] heightMap = HeightMapHelpers.getHeightMap(input);

        int riskLevel = 0;
        for (int y = 0; y < heightMap.length; y++) {
            for (int x = 0; x < heightMap[y].length; x++) {
                if (HeightMapHelpers.isLowest(x, y, heightMap)) {
                    riskLevel += (heightMap[y][x] + 1);
                }
            }
        }
        return riskLevel;
    }

}

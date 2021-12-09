package com.gordonreid.adventofcode2021.december09;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part2 {

    public static int run(List<String> input) {
        Cell[][] downwardFlowMap = HeightMapHelpers.getDownwardFlowMap(input);

        List<Integer> basinSizes = new ArrayList<>();
        for (int y = 0; y < downwardFlowMap.length; y++) {
            for (int x = 0; x < downwardFlowMap[y].length; x++) {
                basinSizes.add(getBasinSize(x, y, downwardFlowMap));
            }
        }
        return basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
    }

    private static int getBasinSize(int x, int y, Cell[][] downwardFlowMap) {
        if (y < 0 || y >= downwardFlowMap.length ||
                x < 0 || x >= downwardFlowMap[0].length ||
                !downwardFlowMap[y][x].isPartOfBasin() || downwardFlowMap[y][x].isVisited()) {
            return 0;
        }
        downwardFlowMap[y][x].visit();
        return 1 + getBasinSize(x, y - 1, downwardFlowMap) +
                getBasinSize(x, y + 1, downwardFlowMap) +
                getBasinSize(x - 1, y, downwardFlowMap) +
                getBasinSize(x + 1, y, downwardFlowMap);
    }


}
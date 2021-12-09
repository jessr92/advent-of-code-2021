package com.gordonreid.adventofcode2021.december09;

import java.util.Comparator;
import java.util.List;

public class Part2 {

    public static int run(List<String> input) {
        HeightMap heightMap = new HeightMap(input);
        List<Integer> basinSizes = heightMap.getBasinSizes();
        return basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
    }
}
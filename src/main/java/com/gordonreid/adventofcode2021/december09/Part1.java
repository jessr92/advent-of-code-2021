package com.gordonreid.adventofcode2021.december09;

import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        HeightMap heightMap = new HeightMap(input);
        return heightMap.getLowestPoints().stream().reduce(0, (a, b) -> a + 1 + b);
    }

}

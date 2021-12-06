package com.gordonreid.adventofcode2021.december06;

import java.util.List;
import java.util.stream.IntStream;

public class Part2 {

    private static final int DAYS = 256;

    public static long run(List<String> input) {
        LanternFishCounts lanternFishCounts = new LanternFishCounts(input);
        IntStream.range(0, DAYS).forEach(lanternFishCounts::simulateDay);
        return lanternFishCounts.totalCount();
    }
}

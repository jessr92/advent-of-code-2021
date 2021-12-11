package com.gordonreid.adventofcode2021.december11;

import java.util.List;
import java.util.stream.LongStream;

public class Part1 {

    public static long run(List<String> input) {
        Grid grid = new Grid(input);
        return LongStream.range(0, 100)
                .mapToObj(i -> grid.simulateStep())
                .mapToLong(Grid.Result::flashes)
                .sum();
    }
}

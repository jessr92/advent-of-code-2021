package com.gordonreid.adventofcode2021.december11;

import java.util.List;

public class Part2 {
    public static long run(List<String> input) {
        Grid grid = new Grid(input);
        Grid.Result result;
        long stepNumber = 0;
        do {
            result = grid.simulateStep();
            stepNumber++;
        } while (!result.allFlashed());
        return stepNumber;
    }
}

package com.gordonreid.adventofcode2021.december07;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        List<Integer> positions = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).toList();
        return FuelCostCalculator.run(positions, Part1::fuelToReachPosition);
    }

    private static int fuelToReachPosition(int desiredPosition, int currentPosition) {
        // 1 unit of fuel per position move
        return Math.abs(desiredPosition - currentPosition);
    }
}

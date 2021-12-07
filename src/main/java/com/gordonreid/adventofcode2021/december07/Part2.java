package com.gordonreid.adventofcode2021.december07;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static int run(List<String> input) {
        List<Integer> positions = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).toList();
        return FuelCostCalculator.run(positions, Part2::fuelCostForMove);
    }

    private static int fuelCostForMove(int desiredPosition, int currentPosition) {
        // 1 unit of fuel for first position move
        // 2 units of fuel for second position move
        // Linearly increasing from there.
        // Fuel cost is this sum(1...n) where n is the number of position changes
        // This can be calculated by using the formula 1/2 n(n+1).
        int positionChanges = Math.abs(desiredPosition - currentPosition);
        return (positionChanges * (positionChanges + 1)) / 2;
    }
}

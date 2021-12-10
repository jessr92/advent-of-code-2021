package com.gordonreid.adventofcode2021.december07;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        List<Integer> positions = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).sorted().toList();
        int positionCount = positions.size();
        int optimalPosition;
        if (positionCount % 2 == 0) {
            optimalPosition = (positions.get(positionCount / 2) + positions.get(positionCount / 2 - 1)) / 2;
        } else {
            optimalPosition = positions.get(positionCount / 2);
        }
        return fuelToReachPosition(optimalPosition, positions);
    }

    private static int fuelToReachPosition(int desiredPosition, List<Integer> positions) {
        return positions.stream()
                .mapToInt(position -> fuelCostForMove(desiredPosition, position))
                .sum();
    }

    private static int fuelCostForMove(int desiredPosition, int currentPosition) {
        // 1 unit of fuel per position move
        return Math.abs(desiredPosition - currentPosition);
    }
}

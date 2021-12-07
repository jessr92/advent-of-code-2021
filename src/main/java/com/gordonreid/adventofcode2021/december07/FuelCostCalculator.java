package com.gordonreid.adventofcode2021.december07;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

@UtilityClass
public class FuelCostCalculator {

    public static int run(List<Integer> positions, BiFunction<Integer, Integer, Integer> fuelCostForMove) {
        int maxPosition = positions.stream().max(Integer::compareTo).orElse(0);
        return IntStream.rangeClosed(0, maxPosition)
                .map(position -> fuelToReachPosition(position, positions, fuelCostForMove))
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    private static int fuelToReachPosition(int desiredPosition, List<Integer> positions, BiFunction<Integer, Integer, Integer> fuelCostForMove) {
        return positions.stream()
                .map(position -> fuelCostForMove.apply(desiredPosition, position))
                .reduce(0, Integer::sum);
    }
}

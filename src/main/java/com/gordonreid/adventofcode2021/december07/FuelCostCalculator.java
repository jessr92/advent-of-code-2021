package com.gordonreid.adventofcode2021.december07;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.BiFunction;

@UtilityClass
public class FuelCostCalculator {

    public static int run(List<Integer> positions, BiFunction<Integer, Integer, Integer> fuelCostForMove) {
        int optimalFuelCost = Integer.MAX_VALUE;
        int maxPosition = positions.stream().max(Integer::compareTo).orElse(0);
        for (int position = 0; position < maxPosition; position++) {
            int fuelCost = fuelToReachPosition(position, positions, fuelCostForMove);
            if (fuelCost < optimalFuelCost) {
                optimalFuelCost = fuelCost;
            }
        }
        return optimalFuelCost;
    }

    private static int fuelToReachPosition(int desiredPosition, List<Integer> positions, BiFunction<Integer, Integer, Integer> fuelCostForMove) {
        return positions.stream().map(position -> fuelCostForMove.apply(desiredPosition, position)).reduce(0, Integer::sum);
    }
}

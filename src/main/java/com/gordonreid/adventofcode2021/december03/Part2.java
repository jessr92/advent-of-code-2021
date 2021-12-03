package com.gordonreid.adventofcode2021.december03;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static int run(List<String> input) {
        String oxygenGeneratorRating = getOxygenGeneratorRating(input);
        String co2ScrubberRating = getCo2ScrubberRating(input);
        int oxygenValue = BinaryHelpers.binaryToInt(oxygenGeneratorRating);
        int co2Value = BinaryHelpers.binaryToInt(co2ScrubberRating);
        return oxygenValue * co2Value;
    }

    private static String getOxygenGeneratorRating(List<String> input) {
        int binaryLength = input.get(0).length();
        List<String> binaryValues = new ArrayList<>(input);
        for (int i = 0; i < binaryLength; i++) {
            if (binaryValues.size() == 1) {
                break;
            }
            List<BinaryCount> counts = BinaryCount.countAll(binaryValues);
            BinaryCount count = counts.get(i);
            char valueToKeep = count.equallyCommon() ? '1' : count.mostCommonBinaryDigit().charAt(0);
            int position = i;
            binaryValues = binaryValues.stream()
                    .filter(binaryValue -> binaryValue.charAt(position) == valueToKeep)
                    .collect(Collectors.toList());
        }
        return Iterables.getOnlyElement(binaryValues);
    }

    private static String getCo2ScrubberRating(List<String> input) {
        int binaryLength = input.get(0).length();
        List<String> binaryValues = new ArrayList<>(input);
        for (int i = 0; i < binaryLength; i++) {
            if (binaryValues.size() == 1) {
                break;
            }
            List<BinaryCount> counts = BinaryCount.countAll(binaryValues);
            BinaryCount count = counts.get(i);
            char valueToKeep = count.equallyCommon() ? '0' : count.leastCommonBinaryDigit().charAt(0);
            int position = i;
            binaryValues = binaryValues.stream()
                    .filter(binaryValue -> binaryValue.charAt(position) == valueToKeep)
                    .collect(Collectors.toList());
        }
        return Iterables.getOnlyElement(binaryValues);
    }
}

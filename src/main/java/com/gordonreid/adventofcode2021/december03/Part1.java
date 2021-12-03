package com.gordonreid.adventofcode2021.december03;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public static int run(List<String> input) {
        List<BinaryCount> counts = BinaryCount.countAll(input);
        String gammaBinary = counts.stream().map(BinaryCount::mostCommonBinaryDigit).collect(Collectors.joining());
        String epsilonBinary = counts.stream().map(BinaryCount::leastCommonBinaryDigit).collect(Collectors.joining());
        int gammaValue = BinaryHelpers.binaryToInt(gammaBinary);
        int epsilonValue = BinaryHelpers.binaryToInt(epsilonBinary);
        return gammaValue * epsilonValue;
    }
}

package com.gordonreid.adventofcode2021.december03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryCount {
    private int zeros;
    private int ones;

    public void count(char i) {
        if (i == '0') {
            zeros++;
        } else if (i == '1') {
            ones++;
        }
    }

    public String mostCommonBinaryDigit() {
        return zeros > ones ? "0" : "1";
    }

    public String leastCommonBinaryDigit() {
        return zeros > ones ? "1" : "0";
    }

    public boolean equallyCommon() {
        return zeros == ones;
    }

    public static List<BinaryCount> countAll(List<String> binaryValues) {
        int binaryLength = binaryValues.get(0).length();
        List<BinaryCount> counts = IntStream.range(0, binaryLength)
                .mapToObj(i -> new BinaryCount())
                .collect(Collectors.toList());
        for (String binaryValue : binaryValues) {
            IntStream.range(0, binaryLength).forEach(i -> counts.get(i).count(binaryValue.charAt(i)));
        }
        return counts;
    }
}

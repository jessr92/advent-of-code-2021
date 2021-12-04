package com.gordonreid.adventofcode2021.december01;

import java.util.List;

final class Part1 {

    // Input: List of input lines representing depth measurements
    // Output: The number of times a depth measurement increases from the previous measurement.
    static int run(List<String> input) {
        int result = 0;
        int previous;
        int current = -1;
        for (String depthMeasurement : input) {
            previous = current;
            current = Integer.parseInt(depthMeasurement);
            if (previous >= 0 && current > previous) {
                result++;
            }
        }
        return result;
    }
}

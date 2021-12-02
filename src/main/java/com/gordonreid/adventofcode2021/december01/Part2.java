package com.gordonreid.adventofcode2021.december01;

import java.util.Arrays;
import java.util.List;

final class Part2 {

    private static final int SLIDING_WINDOW_LENGTH = 3;

    // Input: List of integers representing depth measurements
    // Output: The number of times the sum of measurements in this sliding window (size 3) increases
    static int run(List<Integer> input) {
        int result = 0;
        int[] window = new int[SLIDING_WINDOW_LENGTH];
        int position = 0;
        int previousSum;
        int currentSum = 0;
        int count = 0;
        for (int depthMeasurement : input) {
            window[position] = depthMeasurement;
            position = (position + 1) % window.length;
            count++;
            previousSum = currentSum;
            currentSum = Arrays.stream(window).sum();
            // We don't check the depth measurement sum until we've filled in the entire sliding window _and_ had
            // another measurement after that. Until that point, we don't have enough data points to have a valid
            // previous and current sum to compare.
            if (count > window.length && currentSum > previousSum) {
                result++;
            }
        }
        return result;
    }
}
